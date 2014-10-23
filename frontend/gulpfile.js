var gulp = require('gulp');
var less = require('gulp-less');
var concat = require('gulp-concat');

gulp.task('app', function () {
  gulp.src('src/app/**/*.js')
    .pipe(concat('app.js'))
    .pipe(gulp.dest('public/js'));
});

gulp.task('less', function () {
  gulp.src('src/less/battleship.less')
    .pipe(less())
    .on('error', console.log)
    .pipe(gulp.dest('public/css'));
});

gulp.task('default', ['app', 'less']);

gulp.task('watch', function () {
  gulp.watch('src/less/**/*', ['less']);
  gulp.watch('src/app/**/*', ['app']);
});