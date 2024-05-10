const searchImage = document.querySelector(".hd_search_image")
const searchInput = document.querySelector(".hd_card_search")

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