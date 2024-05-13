const myHeaders = new Headers();

export const postLogin = async (username, password) => {
    const formdata = new FormData();
    formdata.append("username", username);
    formdata.append("password", password);

    const requestOptions = {
        method: "POST",
        headers: myHeaders,
        body: formdata,
    };
    try {
        const response = await fetch(`/login`, requestOptions);
        if(response && response.status === 200) {
            const accessToken = await response.headers.get('Authorization').split(' ')[1];
            localStorage.setItem('access_token', accessToken)
            return true
        }
    } catch (error) {
        console.log("postLogin error : ",error)
        return null
    }
}

