const generateTransactionURL = () => {
    const userAddr = document.querySelector("#input1").value;
    const selectNumber = document.querySelector("#tendency").value;
    const startDate = document.querySelector("#input3").value;

    const baseURL = "http://13.209.42.180:5000/transaction/generation";
    const requestBody = {
        user_addr: userAddr,
        user_cardnum: "5654338751249986",
        select_num: selectNumber,
        start_date: startDate
    };

    return {url: baseURL, body: JSON.stringify(requestBody)};
};

const submitButton = document.querySelector("#submit");

submitButton.addEventListener("click", async () => {
    const {url, body} = generateTransactionURL();
    try {
        console.log("url", url)
        console.log("body", body)
        const response = await fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: body
        });
        if (response.ok) {
            console.log("Transaction 생성 요청이 성공했습니다.");
        } else {
            console.error("Transaction 생성 요청이 실패했습니다.");
        }
    } catch (error) {
        console.error("네트워크 오류:", error);
    }
});
