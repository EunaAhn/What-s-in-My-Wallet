const myHeaders = new Headers();
myHeaders.append("Content-Type", "application/json");
myHeaders.append("Authorization", `Bearer ${localStorage.getItem("access_token")}`);

// 개인 정보 불러오기
export const postMemberProfile = async () => {
    const raw = JSON.stringify({
        username : localStorage.getItem("memberId"),
        password: "1004",
    });
    const requestOptions = {
        method: "POST",
        headers: myHeaders,
        body: raw,
    };
    try {
        const response = await fetch(`/api/member/profile`, requestOptions);
        const result = await response.json();
        return result.result;

    } catch (error) {
        console.log("postMemberProfile error : ",error)
        return null
    }
}