const myHeaders = new Headers();
myHeaders.append("Content-Type", "application/json");

export const getNewExpenditureList = async () => {
    const requestOptions = {
        method: "GET",
    };
    try {
        const response = await fetch(`/api/expenditure/list`, requestOptions);
        const result = await response.json();
        return result.result;
    } catch (error) {
        console.log("getExpenditureList : ",error)
        return null
    }
}


export const getExpenditureList = async (yearAndMonth) => {
    const raw = JSON.stringify({
        "yearAndMonth": yearAndMonth
    });
    const requestOptions = {
        method: "POST",
        headers: myHeaders,
        body: raw
    };
    try {
        const response = await fetch(`/api/expenditure/list`, requestOptions);
        const result = await response.json();
        return result.result;
    } catch (error) {
        console.log("getCardProductList error : ",error)
        return null
    }
}

export const getExpenditureKeywordList = async (yearAndMonth, keyword) => {
    const raw = JSON.stringify({
        "yearAndMonth": yearAndMonth,
        "keyword": keyword
    });
    const requestOptions = {
        method: "POST",
        headers: myHeaders,
        body: raw
    };
    try {
        const response = await fetch(`/api/expenditure/list`, requestOptions);
        const result = await response.json();
        return result.result;
    } catch (error) {
        console.log("getCardProductList error : ",error)
        return null
    }
}

export const getDailyExpenditureMemo = async (memoId, memo) => {
    const raw = JSON.stringify({
        "memoId": memoId,
        "memo": memo
    });
    const requestOptions = {
        method: "POST",
        headers: myHeaders,
        body: raw
    };
    try {
        const response = await fetch(`/api/expenditure/memo`, requestOptions);
        const result = await response.json();
        return result.result;
    } catch (error) {
        console.log("getCardProductList error : ",error)
        return null
    }
}

export const getDailyExpenditureList = async (nowDate) => {
    const raw = JSON.stringify({
        "nowDate": nowDate
    });
    const requestOptions = {
        method: "POST",
        headers: myHeaders,
        body: raw
    };
    try {
        const response = await fetch(`/api/expenditure/detail`, requestOptions);
        const result = await response.json();
        return result.result;
    } catch (error) {
        console.log("getCardProductList error : ",error)
        return null
    }
}

export const getExpenditureSummaryDtoList = async (nowDate) => {
    try {
        const result = await getDailyExpenditureList(nowDate)
        return result.expenditureSummaryDtoList;
    } catch (error) {
        console.log("getExpenditureSummaryDtoList error : ",error)
        return null
    }
}

export const getStoreAddressList = async (nowDate) => {
    try {
        const result = await getDailyExpenditureList(nowDate)
        return result.expenditureSummaryDtoList.map(item => item.storeAddress);
    } catch (error) {
        console.log("getExpenditureSummaryDtoList error : ",error)
        return null
    }
}