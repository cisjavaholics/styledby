import React, {useEffect, useState} from 'react';
import UsersService from '../services/UsersService';

function UserComponent() {

    const [users, setUsers] = useState([])

    useEffect(() => {
        getUsers()
    }, [])

    const getUsers = () => {

        UsersService.getUsers().then((response) => {
            setUsers(response.data)
            console.log(response.data);
        });
    };

    return (
        <div className = "container">

            <h1 className = "text-center"> Users List</h1>

            <table className = "table table-striped">
                <thead>
                <tr>
                    <th> User Id</th>
                    <th> User Name</th>
                    <th> User Email</th>
                    <th> User Businesses</th>
                </tr>

                </thead>
                <tbody>
                {
                    users.map(
                        user =>
                            <tr key = {user.id}>
                                <td> {user.id }</td>
                                <td> {user.username}</td>
                                <td> {user.email }</td>
                                <td> {user.businesses }</td>

                            </tr>

                    )
                }

                </tbody>


            </table>

        </div>
    )
}

export default UserComponent