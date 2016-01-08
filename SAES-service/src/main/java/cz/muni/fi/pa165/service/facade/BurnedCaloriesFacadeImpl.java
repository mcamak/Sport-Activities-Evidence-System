package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.dto.BurnedCaloriesCreateDTO;
import cz.muni.fi.pa165.dto.BurnedCaloriesDTO;
import cz.muni.fi.pa165.facade.BurnedCaloriesFacade;
import cz.muni.fi.pa165.saes.entity.BurnedCalories;
import cz.muni.fi.pa165.service.BurnedCaloriesService;
import cz.muni.fi.pa165.service.mapping.BeanMappingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 *
 * @author Jan S.
 */
@Service
@Transactional
public class BurnedCaloriesFacadeImpl implements BurnedCaloriesFacade {

    @Inject
    private BurnedCaloriesService burnedCaloriesService;

    @Inject
    private BeanMappingService bms;

    @Override
    public Long create(BurnedCaloriesCreateDTO burnedCaloriesCreateDTO) {
        BurnedCalories burnedCalories = bms.mapTo(burnedCaloriesCreateDTO, BurnedCalories.class);
        burnedCaloriesService.create(burnedCalories);
        return burnedCalories.getId();
    }

    @Override
    public BurnedCaloriesDTO findById(Long id) {
        return bms.mapTo(burnedCaloriesService.findById(id), BurnedCaloriesDTO.class);
    }

    @Override
    public List<BurnedCaloriesDTO> findBySportActivity(Long activityId) {
        return bms.mapTo(burnedCaloriesService.findBySportActivity(activityId), BurnedCaloriesDTO.class);
    }

    @Override
    public List<BurnedCaloriesDTO> findAll() {
        return bms.mapTo(burnedCaloriesService.findAll(), BurnedCaloriesDTO.class);
    }

    @Override
    public void update(BurnedCaloriesDTO burnedCaloriesDTO) {
        burnedCaloriesService.update(bms.mapTo(burnedCaloriesDTO, BurnedCalories.class));
    }

    @Override
    public void delete(Long id) {
        burnedCaloriesService.delete(id);
    }
}
