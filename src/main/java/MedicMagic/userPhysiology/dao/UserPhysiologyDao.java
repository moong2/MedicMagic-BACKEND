package MedicMagic.userPhysiology.dao;

import MedicMagic.userCalender.domain.UserCalender;
import MedicMagic.userPhysiology.domain.UserPhysiology;
import MedicMagic.userPhysiology.dto.UserPhysiologyDto;

import java.util.List;

public interface UserPhysiologyDao {
    void add(UserPhysiologyDto userPhysiologyDto);
    List<UserPhysiologyDto> get(String id);
    UserPhysiologyDto getEachIdAndStartPhysiology(String id, String startPhysiology);
    UserPhysiologyDto getEachIdAndEndPhysiology(String id, String endPhysiology);
    UserPhysiologyDto getLastEachId(String id);
    UserPhysiologyDto getNull(String id);
    List<UserPhysiologyDto> getAll();
    List<UserPhysiologyDto> getEachIdLimit6(String id);
    List<UserPhysiologyDto> getEachIdAndMonth(String id, String month);
    void deleteAll();
    void deleteEachIdAndStartPhysiology(String id, String startPhysiology);
    void deleteEachIdAndEndPhysiology(String id, String endPhysiology);
    int getCount();
    int getCountEachId(String id);
    int getCountEachIdAndStartPhysiology(String id, String startPhysiology);
    int getCountEachIdAndEndPhysiology(String id, String endPhysiology);
    int getNullCount(String id);
    void update(UserPhysiologyDto userPhysiologyDto);
}
