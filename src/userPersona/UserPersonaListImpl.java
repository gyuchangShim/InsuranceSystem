package userPersona;

import java.util.ArrayList;
import java.util.List;

public class UserPersonaListImpl implements UserPersonaList{

    private List<UserPersona> userPersonaList;

    public UserPersonaListImpl(){
        userPersonaList = new ArrayList<>();
    }

    @Override
    public void add(UserPersona userPersona) {
        userPersonaList.add(userPersona);
    }

    @Override
    public void delete(int userPersonaId) {

    }

    @Override
    public UserPersona retrieve(int userPersonaId) {
        return null;
    }

    @Override
    public List<UserPersona> retrieveAll() {
        return userPersonaList;
    }

    @Override
    public void update(UserPersona userPersona) {

    }
}
