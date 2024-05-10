document.addEventListener("DOMContentLoaded", () => {
    localStorage.setItem('clickedmenu', ".side_profile");
})

const user_dialog = document.querySelector('.user_modal');
const user = document.querySelector('.user')

user.addEventListener('click', event => {
    user_dialog.showModal();
});

const card_dialog = document.querySelector('.card_modal');
const card = document.querySelector('.card')

card.addEventListener('click', event => {
    card_dialog.showModal();
});

const like_dialog = document.querySelector('.like_modal');
const like = document.querySelector('.like')

like.addEventListener('click', event => {
    like_dialog.showModal();
});

const closeButtons = document.querySelectorAll('.close_btn')
closeButtons.forEach((item) => {
    item.addEventListener('click', () => {
        card_dialog.close();
        user_dialog.close();
        like_dialog.close();
    })
})