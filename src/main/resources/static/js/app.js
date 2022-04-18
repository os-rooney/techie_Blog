const menuBtn = document.querySelector('.menu-btn');
const navList = document.querySelector('.nav-list');
let menuOpen = false;

menuBtn.addEventListener('click', ()=>{
   if(!menuOpen){
      menuBtn.classList.add('open');
      navList.classList.add('open-list');
      navList.classList.remove('hide');
      menuOpen = true;
   } else {
      menuBtn.classList.remove('open');
      navList.classList.remove('open-list');
      navList.classList.add('hide');
      menuOpen = false;
   }
});
