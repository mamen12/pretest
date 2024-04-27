import axios from 'axios';

  
axios.defaults.baseURL = 'http://localhost:8080'
axios.defaults.headers.post["Content-Type"] = 'application/json'

const createPayload = (data) => {
    return {
        request_header: {
            request_id: generateGuid(),
            chanel: 'API'
        },
        request_payload: data
    };
};

const generateGuid = () => {
    return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
        var r = Math.random() * 16 | 0, v = c == 'x' ? r : (r & 0x3 | 0x8);
        return v.toString(16);
    });
};

export const setAuthHeader = (token) => {
    if (token !== null) {
      window.localStorage.setItem("auth_token", token);
    } else {
      window.localStorage.removeItem("auth_token");
    }
};

export const request = (method, url, data) => {
    return axios({
        method: method,
        url: url,
        data: createPayload(data)
    });
}