const myHeaders = new Headers();
myHeaders.append("Content-Type", "application/json");

export const getCardProductList = async (startNum, endNum, keyWord) => {
    const raw = JSON.stringify({
        startNum : startNum,
        endNum: endNum,
        keyWord: keyWord
    });
    const requestOptions = {
        method: "POST",
        headers: myHeaders,
        body: raw,
    };
    try {
        const response = await fetch(`/api/cardproduct/list`, requestOptions);
        const result = await response.json();
        console.log(raw)
        return result.result;

    } catch (error) {
        console.log("getCardProductList error : ",error)
        return null
    }
}