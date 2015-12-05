/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.facade;

import cz.muni.fi.pa165.dto.ActivityRecordDTO;

/**
 *
 * @author B. Bajtosova
 */
public interface ActivityRecordFacade {

    public Long create(ActivityRecordDTO activityRecordCreateDTO);

    public ActivityRecordDTO findById(Long activityRecordId);

    public void update(ActivityRecordDTO activityRecordDTO);

    public void delete(Long activityRecordId);

}
