const myHeaders = new Headers();
myHeaders.append("Content-Type", "application/json");
myHeaders.append("Authorization", `Bearer ${localStorage.getItem("access_token")}`);

export const getExpenditureTendency = async (yearAndMonth) => {
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
        console.log("getExpenditureTendency error: ", error);
        return null;
    }
}

export const getTotalAmountByTime = async (yearAndMonth) => {
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
        console.log("getTotalAmountByTime error: ", error);
        return null;
    }
}

export const getTotalAmountBy12Month = async (year) => {
    const raw = JSON.stringify({
        "year": year
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
        console.log("getTotalAmountBy12Month error: ", error);
        return null;
    }
}

export const getSavingAmountGoal = async (yearAndMonth) => {
    const raw = JSON.stringify({
        "yearAndMonth" : yearAndMonth
    });
    const requestOptions = {
        method: "POST",
        headers: myHeaders,
        body: raw
    };
    try {
        const response = await fetch(`/api/expenditure/savingamount`, requestOptions);
        const result = await response.json();
        return result.result;
    } catch (error) {
        console.log("getSavingAmountGoal error: ", error);
        return null;
    }
}

export const getTotalCountByTimePeriod = async (yearAndMonth, endHour, startHour) => {
    const raw = JSON.stringify({
        "yearAndMonth" : yearAndMonth,
        "endHour" : endHour,
        "startHour" : startHour
    });
    const requestOptions = {
        method: "POST",
        headers: myHeaders,
        body: raw
    };
    try {
        const response = await fetch(`/api/expenditure/totalcountbytimeperiod`, requestOptions);
        const result = await response.json();
        return result.result;
    } catch (error) {
        console.log("getTotalCountByTimePeriod error: ", error);
        return null;
    }
}
