import {User} from "../types/external";

export const getUsers = async (): Promise<User[]> => {
    const response = await fetch('/api/users');
    return await response.json()
}

export const saveUser = async (data: User): Promise<void> => {
    await fetch('/api/users', {
        method: "POST",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    });
}

export const updateUser = async (data: User): Promise<void> => {
    await fetch(`/api/users/${data.id}`, {
        method: "PUT",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    });
}

export const deleteUser = async (userId: string): Promise<void> => {
    await fetch(`/api/users/${userId}`, {
        method: "DELETE"
    });
}

