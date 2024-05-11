document.addEventListener("DOMContentLoaded", () => {
    localStorage.setItem('clickedmenu', ".side_cardlist");
})

document.addEventListener("DOMContentLoaded", () => {
    const cardLists  = document.querySelectorAll(".card-list")
    cardLists.forEach((cardList) => {
        cardList.addEventListener("mouseover", () => {
            const cardTitle = cardList.querySelector(".card-title");
            cardTitle.style.color = "#9288dd";
        })

        cardList.addEventListener("mouseout", () => {
            const cardTitle = cardList.querySelector(".card-title");
            cardTitle.style.color = "#3b3a45";
        })
    })
})

