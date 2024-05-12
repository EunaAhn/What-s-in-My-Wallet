const myHeaders = new Headers();
myHeaders.append("Content-Type", "application/json");

export const getCardProductList = async () => {
    const raw = JSON.stringify({
        startNum : 1,
        endNum: 10
    });
    const requestOptions = {
        method: "POST",
        headers: myHeaders,
        body: raw,
    };
    try {
        const response = await fetch(`/api/cardproduct/list`, requestOptions);
        const result = await response.json();
        console.log(result.result)
        return result.result;
    } catch (error) {
        console.log("getCardProductList error : ",error)
        return null
    }
}

