const myHeaders = new Headers();
myHeaders.append("Content-Type", "application/json");

export const getExpendituretendency = async (yearAndMonth) => {
    const raw = JSON.stringify({
        "yearAndMonth": yearAndMonth
    });
    const requestOptions = {
        method: "POST",
        headers: myHeaders,
        body: raw
    };
    try {
        const response = await fetch(`/api/expendituretendency/yearandmonth`, requestOptions);
        const result = await response.json();
        return result.result;
    } catch (error) {
        console.log("getExpendituretendency error : ",error)
        return null
    }
}

export const getTotalAmountBytime = async (yearAndMonth) => {
    const raw = JSON.stringify({
        "yearAndMonth": yearAndMonth
    });
    const requestOptions = {
        method: "POST",
        headers: myHeaders,
        body: raw
    };
    try {
        const response = await fetch(`/api/expenditure/totalamountbytime`, requestOptions);
        const result = await response.json();
        return result.result;
    } catch (error) {
        console.log("getTotalAmountBytime error : ",error)
        return null
    }
}

export const geTotalAmountBy12month = async (yearAndMonth) => {
    const raw = JSON.stringify({
        "yearAndMonth": yearAndMonth
    });
    const requestOptions = {
        method: "POST",
        headers: myHeaders,
        body: raw
    };
    try {
        const response = await fetch(`/api/expenditure/totalamountby12month`, requestOptions);
        const result = await response.json();
        return result.result;
    } catch (error) {
        console.log("getTotalAmountBytime error : ",error)
        return null
    }
}

