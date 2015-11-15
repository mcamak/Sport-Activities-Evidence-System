package cz.muni.fi.pa165.service.facade;

import cz.muni.fi.pa165.facade.SportActivityFacade;
import cz.muni.fi.pa165.service.SportActivityService;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author MajoCAM
 */
@Service
@Transactional
public class SportActivityFacadeImpl implements SportActivityFacade {
    
    @Inject
    private SportActivityService activityService;
    
}
