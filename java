// بيانات نموذجية للمنشورات
const postsData = [
    {
        id: 1,
        user: {
            name: "أحمد محمد",
            username: "@ahmed_moh",
            image: "https://randomuser.me/api/portraits/men/32.jpg"
        },
        content: "مرحبًا بالجميع! هذا أول منشور لي على هذه المنصة الرائعة. أنا متحمس جدًا لكوني جزءًا من هذا المجتمع.",
        image: "https://source.unsplash.com/random/600x400?nature",
        likes: 24,
        comments: 5,
        shares: 2,
        time: "منذ ساعتين"
    },
    {
        id: 2,
        user: {
            name: "ليلى خالد",
            username: "@laila_kh",
            image: "https://randomuser.me/api/portraits/women/44.jpg"
        },
        content: "شاركوني آرائكم حول أحدث تقنيات الويب التي تعلمتموها مؤخرًا. أنا مهتمة جدًا بـ React.js و Vue.js!",
        likes: 42,
        comments: 8,
        shares: 3,
        time: "منذ 4 ساعات"
    },
    {
        id: 3,
        user: {
            name: "محمد علي",
            username: "@mohamed_ali",
            image: "https://randomuser.me/api/portraits/men/75.jpg"
        },
        content: "هل لديكم أي نصائح لتحسين أداء مواقع الويب؟ أبحث عن طرق لتحسين سرعة تحميل الموقع.",
        image: "https://source.unsplash.com/random/600x400?technology",
        likes: 18,
        comments: 7,
        shares: 1,
        time: "منذ 6 ساعات"
    }
];

// دالة لعرض المنشورات
function renderPosts() {
    const postsContainer = document.querySelector('.posts');
    postsContainer.innerHTML = '';
    
    postsData.forEach(post => {
        const postElement = document.createElement('div');
        postElement.className = 'post';
        postElement.innerHTML = `
            <div class="post-header">
                <div class="post-user-img">
                    <img src="${post.user.image}" alt="${post.user.name}">
                </div>
                <div class="post-user-info">
                    <h4>${post.user.name}</h4>
                    <p>${post.user.username} · ${post.time}</p>
                </div>
            </div>
            <div class="post-content">
                <p>${post.content}</p>
            </div>
            ${post.image ? `<img src="${post.image}" class="post-image" alt="صورة المنشور">` : ''}
            <div class="post-actions">
                <div class="post-action">
                    <i class="far fa-heart"></i>
                    <span>${post.likes}</span>
                </div>
                <div class="post-action">
                    <i class="far fa-comment"></i>
                    <span>${post.comments}</span>
                </div>
                <div class="post-action">
                    <i class="fas fa-share"></i>
                    <span>${post.shares}</span>
                </div>
            </div>
        `;
        
        postsContainer.appendChild(postElement);
    });
}

// دالة لإضافة منشور جديد
function addPost(content, imageUrl = null) {
    const newPost = {
        id: postsData.length + 1,
        user: {
            name: "سارة أحمد",
            username: "@sara_ahmed",
            image: "https://randomuser.me/api/portraits/women/63.jpg"
        },
        content: content,
        image: imageUrl,
        likes: 0,
        comments: 0,
        shares: 0,
        time: "الآن"
    };
    
    postsData.unshift(newPost);
    renderPosts();
}

// حدث لإضافة منشور جديد
document.addEventListener('DOMContentLoaded', () => {
    renderPosts();
    
    const postForm = document.querySelector('.post-form');
    const postTextarea = postForm.querySelector('textarea');
    const postBtn = postForm.querySelector('.post-btn');
    
    postBtn.addEventListener('click', () => {
        const content = postTextarea.value.trim();
        if (content) {
            addPost(content);
            postTextarea.value = '';
        }
    });
    
    // يمكنك إضافة المزيد من الأحداث هنا
});

// تفاعلات الإعجاب والتعليق والمشاركة
document.addEventListener('click', (e) => {
    if (e.target.classList.contains('fa-heart') || e.target.classList.contains('post-action')) {
        const heartIcon = e.target.classList.contains('fa-heart') ? e.target : e.target.querySelector('.fa-heart');
        if (heartIcon) {
            heartIcon.classList.toggle('far');
            heartIcon.classList.toggle('fas');
            heartIcon.classList.toggle('liked');
            
            const likesCount = heartIcon.nextElementSibling;
            if (likesCount) {
                const currentLikes = parseInt(likesCount.textContent);
                if (heartIcon.classList.contains('liked')) {
                    likesCount.textContent = currentLikes + 1;
                } else {
                    likesCount.textContent = currentLikes - 1;
                }
            }
        }
    }
});
