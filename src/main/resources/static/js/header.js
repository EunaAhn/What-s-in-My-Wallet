const searchImage = document.querySelector(".hd_search_image")
const searchInput = document.querySelector("#hd_card_search")

searchImage.addEventListener("click", () => {
    searchInput.focus()
    searchImage.classList.add("focusing")
})

searchInput.addEventListener("focus",()=>{
    searchImage.classList.add("focusing")
})

searchInput.addEventListener("blur",()=>{
    searchImage.classList.remove("focusing")
})

const hdUserName = document.querySelector(".hd_user_name")
const hdUserEmail = document.querySelector(".hd_user_email")

hdUserName.innerHTML = localStorage.getItem("memberName")
hdUserEmail.innerHTML = localStorage.getItem("memberId")
