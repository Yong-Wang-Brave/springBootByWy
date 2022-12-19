package com.wy.demo.设计模式.工厂模式.抽象工厂;

public interface CourseFactory {
    INote createNote();
    IVideo createVideo();
}
