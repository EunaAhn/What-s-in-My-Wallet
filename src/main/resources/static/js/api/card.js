const myHeaders = new Headers();
myHeaders.append("Content-Type", "application/json");

// 카드 상품 전체 목록 조회(검색옵션)
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
        return result.result;

    } catch (error) {
        console.log("getCardProductList error : ",error)
        return null
    }
}

// 인기카드 조회
export const getCardProductTop = async () => {
    const raw = JSON.stringify({
        "cardId": 1
    });

    const requestOptions = {
        method: "POST",
        headers: myHeaders,
        body: raw,
    };
    try {
        const response = await fetch(`/api/cardproduct/top4list`, requestOptions);
        const result = await response.json();
        return result.result;

    } catch (error) {
        console.log("getCardProductTop error : ",error)
        return null
    }
}

// 내 카드 목록 조회
export const getMyCard = async () => {
    const raw = JSON.stringify({
        "memberName": "김우재",
        "memberContactNumber": "01089387607"
    });

    const requestOptions = {
        method: "POST",
        headers: myHeaders,
        body: raw
    };
    try {
        const response = await fetch(`/mycard`, requestOptions);
        const result = await response.json();
        return result.result;

    } catch (error) {
        console.log("getMyCard error : ",error)
        return null
    }
}

// 등록된 내 카드 목록 조회
export const postRegistrationcardRegister = async (selectedCardIdList) => {
    const raw = JSON.stringify(selectedCardIdList);

    const requestOptions = {
        method: "POST",
        headers: myHeaders,
        body: raw
    };
    try {
        const response = await fetch(`/api/registrationcard/register`, requestOptions);
        console.log(response)
        // const result = await response.json();
        // return result.result;

    } catch (error) {
        console.log("postRegistrationcardRegister error : ",error)
        return null
    }
}

// 등록된 내 카드 불러오기
export const getRegistrationCardList = async () => {
    const requestOptions = {
        method: "GET",
        headers: myHeaders,
    };
    try {
        const response = await fetch(`/api/registrationcard/list`, requestOptions);
        const result = await response.json();
        return result.result;

    } catch (error) {
        console.log("getRegistrationCardList error : ",error)
        return null
    }
}

