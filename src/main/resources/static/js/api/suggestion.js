const myHeaders = new Headers();
myHeaders.append("Content-Type", "application/json");

export const getExpenditureRatioByCategory = async (yearAndMonth) => {
    const raw = JSON.stringify({
        "yearAndMonth": yearAndMonth
    });
    const requestOptions = {
        method: "POST",
        headers: myHeaders,
        body: raw
    };
    try {
        const response = await fetch(`/api/expenditure/ratioByCategory`, requestOptions);
        const result = await response.json();
        return result.result;
    } catch (error) {
        console.log("getExpenditureRatioCategory error : ",error)
        return null
    }
}

export const getCategoryNameList = async (yearAndMonth) => {
    try {
        const result = await getExpenditureRatioByCategory(yearAndMonth)
        return result.map(item => item.categoryName);
    } catch (error) {
        console.log("getCategoryNameList error : ",error)
        return null
    }
}

export const getExpenditureRatioList = async (yearAndMonth) => {
    try {
        const result = await getExpenditureRatioByCategory(yearAndMonth)
        return result.map(item => item.expenditrueRatio);
    } catch (error) {
        console.log("getExpenditureRatioList error : ",error)
        return null
    }
}

export const getExpenditureCountForCategory = async (yearAndMonth) => {
    const raw = JSON.stringify({
        "yearAndMonth": yearAndMonth
    });
    const requestOptions = {
        method: "POST",
        headers: myHeaders,
        body: raw
    };
    try {
        const response = await fetch(`/api/expenditure/countforcategory`, requestOptions);
        const result = await response.json();
        return result.result;
    } catch (error) {
        console.log("getExpenditureCountForCategory error : ",error)
        return null
    }
}

export const getTotalAmountList = async (yearAndMonth) => {
    try {
        const result = await getExpenditureCountForCategory(yearAndMonth)
        return result.map(item => item.TOTALAMOUNT);
    } catch (error) {
        console.log("getTotalAmountList error : ",error)
        return null
    }
}

export const getLineCategoryNameList = async (yearAndMonth) => {
    try {
        const result = await getExpenditureCountForCategory(yearAndMonth)
        return result.map(item => item.CATEGORYNAME);
    } catch (error) {
        console.log("getLineCategoryNameList error : ",error)
        return null
    }
}