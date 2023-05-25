package com.effectiveJava.chapter4.item23;

public class Item23 {

    private enum Shape{
        RECTANGLE, CIRCLE;
    }
    private static class Figure{
        enum Shape {RECTANGLE, CIRCLE}; // 태그들
        
        final Shape shape;
        
        // 태그별로 쓰이는 필드
        double length;
        double width;
        double radius;
        
        // 태그별 생성자
        Figure(double radius){
            shape = Shape.CIRCLE;
            this.radius = radius;
        }
        
        // 태그별 생성자
        Figure(double length, double width){
            shape = Shape.RECTANGLE;
            this.length = length;
            this.width = width;
        }
        
        // 태그별로 다르게 동작하는 메서드
        double area(){
            switch(shape){
                case RECTANGLE:
                    return length * width;
                case CIRCLE:
                    return Math.PI * (radius * radius);
                default:
                    throw new AssertionError(shape);
            }
        }
    }

    abstract class Figure2{
        abstract double area();
    }

    private class Circle extends Figure2{
        final double radius;

        Circle(double radius){
            this.radius = radius;
        }

        @Override
        double area(){
            return Math.PI * (radius * radius);
        }
    }

    private class Rectangle extends Figure2{
        final double length;
        final double width;

        Rectangle(double length, double width){
            this.length = length;
            this.width = width;
        }

        @Override
        double area(){
            return length * width;
        }
    }
}
