const BaseUrl = "localhost:8090"
const myHeaders = new Headers();
myHeaders.append("Content-Type", "application/json");

export const getCardProductList  = () => {
    const raw = JSON.stringify({
        "cardCompanyName": null,
        "benefit": "",
        "cardName": ""
    });
    const requestOptions = {
        method: "GET",
        headers: myHeaders,
        body: raw,
        redirect: "follow"
    };
    fetch(`${BaseUrl}/mydatatest`, requestOptions)
        .then((response) => response.text())
        .then((result) => console.log(result))
        .catch((error) => console.error(error));
}

