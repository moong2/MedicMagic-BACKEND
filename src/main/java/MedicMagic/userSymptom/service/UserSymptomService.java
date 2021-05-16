package MedicMagic.userSymptom.service;

import MedicMagic.userCalender.domain.UserCalender;
import MedicMagic.userSymptom.domain.UserSymptom;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface UserSymptomService {
    void add(UserSymptom userSymptom);
    @Transactional(readOnly = true)
    UserSymptom get(String id, String date);
    @Transactional(readOnly = true)
    List<UserSymptom> getAll();
    @Transactional(readOnly = true)
    List<UserSymptom> getEachId(String id);
    @Transactional(readOnly = true)
    List<String> getSymptomTrue(UserSymptom userSymptom);
    void deleteAll();
    void deleteEachIdAndDate(String id, String date);
    void update(UserSymptom userSymptom);
    void updateUserCalenderIfSymptomIsFalse(UserSymptom userSymptom, UserCalender userCalender);
}
