var gulp = require('gulp');
var sass = require('gulp-sass');
var concat = require('gulp-concat');

gulp.task('app', function () {
  gulp.src('src/app/**/*.js')
    .pipe(concat('app.js'))
    .pipe(gulp.dest('public/js'));
});

gulp.task('sass', function () {
  gulp.src('src/scss/*.scss')
    .pipe(sass())
    .on('error', console.log)
    .pipe(gulp.dest('public/css'));
});

gulp.task('default', ['app', 'sass']);

gulp.task('watch', function () {
  gulp.watch('src/scss/**/*', ['less']);
  gulp.watch('src/app/**/*', ['app']);
});