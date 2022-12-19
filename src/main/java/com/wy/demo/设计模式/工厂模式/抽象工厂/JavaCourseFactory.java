package com.wy.demo.设计模式.工厂模式.抽象工厂;

public class JavaCourseFactory implements CourseFactory{
    @Override
    public INote createNote() {
        return new JavaNote();
    }

    @Override
    public IVideo createVideo() {
        return new JvavaVideo();
    }
}
