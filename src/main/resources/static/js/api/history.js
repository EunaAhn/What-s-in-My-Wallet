const myHeaders = new Headers();
myHeaders.append("Content-Type", "application/json");

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
        const response = await fetch(`/api/expenditure/daily`, requestOptions);
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