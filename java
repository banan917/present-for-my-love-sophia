const canvas = document.getElementById('hearts');
const ctx = canvas.getContext('2d');
canvas.width = window.innerWidth;
canvas.height = window.innerHeight;

const hearts = [];

class Heart {
  constructor() {
    this.x = Math.random() * canvas.width;
    this.y = canvas.height + Math.random() * 100;
    this.size = Math.random() * 30 + 10;
    this.speed = Math.random() * 1.5 + 0.5;
    this.opacity = Math.random() * 0.5 + 0.3;
    this.drift = Math.random() * 1 - 0.5;
  }

  draw() {
    ctx.globalAlpha = this.opacity;
    ctx.beginPath();
    ctx.moveTo(this.x, this.y);
    ctx.bezierCurveTo(
      this.x - this.size / 2, this.y - this.size / 2,
      this.x - this.size, this.y + this.size / 3,
      this.x, this.y + this.size
    );
    ctx.bezierCurveTo(
      this.x + this.size, this.y + this.size / 3,
      this.x + this.size / 2, this.y - this.size / 2,
      this.x, this.y
    );
    ctx.fillStyle = 'rgba(255, 105, 180, 0.9)';
    ctx.fill();
    ctx.globalAlpha = 1;
  }

  update() {
    this.y -= this.speed;
    this.x += this.drift;
    if (this.y < -10) {
      this.y = canvas.height + 10;
      this.x = Math.random() * canvas.width;
    }
    this.draw();
  }
}

function init() {
  for (let i = 0; i < 100; i++) {
    hearts.push(new Heart());
  }
  animate();
}

function animate() {
  ctx.clearRect(0, 0, canvas.width, canvas.height);
  hearts.forEach(heart => heart.update());
  requestAnimationFrame(animate);
}

window.addEventListener('resize', () => {
  canvas.width = window.innerWidth;
  canvas.height = window.innerHeight;
});

init();
