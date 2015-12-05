package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.saes.UserFilter;
import cz.muni.fi.pa165.saes.dao.UserDao;
import cz.muni.fi.pa165.saes.entity.User;
import exceptions.SaesDataAccessException;
import exceptions.SaesServiceException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.List;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Jan S.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Inject
    private UserDao userDao;

    @Override
    public Long signIn(User user, String password) {
        if (user == null) {
            throw new IllegalArgumentException("User is null. ");
        }
        if (user.getId() != null) {
            throw new IllegalArgumentException("User ID is not null. ");
        }
        try {
            userDao.createUser(user);
        } catch (Exception e) {
            throw new SaesDataAccessException("Failed when creating user. ", e);
        }

        return user.getId();
    }

    @Override
    public boolean authenticate(Long userId, String password) {
        User user = findById(userId);
        return validatePassword(password, user.getPasswordHash());
    }

    @Override
    public void changePassword(Long userId, String oldPassword, String newPassword) {
        User user = findById(userId);
        if (validatePassword(oldPassword, user.getPasswordHash())) {
            user.setPasswordHash(createHash(newPassword));
            userDao.updateUser(user);
        } else {
            throw new SaesServiceException("Your old password doesn't match with stored password. ");
        }
    }

    @Override
    public void remove(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User is null. ");
        }
        if (user.getId() == null) {
            throw new IllegalArgumentException("User ID is null. Cannot be removed. ");
        }
        try {
            userDao.deleteUser(user);
        } catch (Exception e) {
            throw new SaesDataAccessException("Failed when deleting user. ", e);
        }
    }

    @Override
    public User findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID is null. ");
        }
        try {
            return userDao.findUser(id);
        } catch (Exception e) {
            throw new SaesDataAccessException("Failed when finding user. ", e);
        }
    }

    @Override
    public void update(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User is null. ");
        }
        if (user.getId() == null) {
            throw new IllegalArgumentException("User ID is null. Persist user first. ");
        }
        User old = userDao.findUser(user.getId());
        if (old.getAge() >= user.getAge()) {
            throw new SaesServiceException("New age must be higher than old value. ");
        }
        if (!old.getSex().equals(user.getSex())) {
            throw new SaesServiceException("You cannot change your gender. ");
        }
        try {
            userDao.updateUser(user);
        } catch (Exception e) {
            throw new SaesDataAccessException("Failed when updating user. ", e);
        }
    }

    @Override
    public List<User> findAll() {
        try {
            return userDao.findAllUsers();
        } catch (Exception e) {
            throw new SaesDataAccessException("Failed when finding all users. ", e);
        }
    }

    @Override
    public List<User> getUsersByFilter(UserFilter filter) {
        try {
            return userDao.findUsersByParameters(filter);
        } catch (Exception e) {
            throw new SaesDataAccessException("Failed when finding user by parameters. ", e);
        }
    }

    private static String createHash(String password) {
        final int SALT_BYTE_SIZE = 24;
        final int HASH_BYTE_SIZE = 24;
        final int PBKDF2_ITERATIONS = 1000;
        // Generate a random salt
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_BYTE_SIZE];
        random.nextBytes(salt);
        // Hash the password
        byte[] hash = pbkdf2(password.toCharArray(), salt, PBKDF2_ITERATIONS, HASH_BYTE_SIZE);
        // format iterations:salt:hash
        return PBKDF2_ITERATIONS + ":" + toHex(salt) + ":" + toHex(hash);
    }

    private static byte[] pbkdf2(char[] password, byte[] salt, int iterations, int bytes) {
        try {
            PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, bytes * 8);
            return SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256").generateSecret(spec).getEncoded();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean validatePassword(String password, String correctHash) {
        if (password == null) {
            return false;
        }
        if (correctHash == null) {
            throw new IllegalArgumentException("password hash is null");
        }
        String[] params = correctHash.split(":");
        int iterations = Integer.parseInt(params[0]);
        byte[] salt = fromHex(params[1]);
        byte[] hash = fromHex(params[2]);
        byte[] testHash = pbkdf2(password.toCharArray(), salt, iterations, hash.length);
        return slowEquals(hash, testHash);
    }

    /**
     * Compares two byte arrays in length-constant time. This comparison method
     * is used so that password hashes cannot be extracted from an on-line
     * system using a timing attack and then attacked off-line.
     *
     * @param a the first byte array
     * @param b the second byte array
     * @return true if both byte arrays are the same, false if not
     */
    private static boolean slowEquals(byte[] a, byte[] b) {
        int diff = a.length ^ b.length;
        for (int i = 0; i < a.length && i < b.length; i++) {
            diff |= a[i] ^ b[i];
        }
        return diff == 0;
    }

    private static byte[] fromHex(String hex) {
        byte[] binary = new byte[hex.length() / 2];
        for (int i = 0; i < binary.length; i++) {
            binary[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return binary;
    }

    private static String toHex(byte[] array) {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        return paddingLength > 0 ? String.format("%0" + paddingLength + "d", 0) + hex : hex;
    }
}
