package userPersona;

import java.util.List;

public interface UserPersonaList {
    void add(UserPersona userPersona);
    void delete(int userPersonaId);
    UserPersona retrieve(int userPersonaId);
    List<UserPersona> retrieveAll();
    void update(UserPersona userPersona);
}
