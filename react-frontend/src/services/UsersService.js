import axios from 'axios';

const USER_API_BASE_URL = 'http://localhost:8080/api/users';

class UsersService{

    getUsers(){
        return axios.get(USER_API_BASE_URL);
    }
}

export default new UsersService();