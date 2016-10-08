/**
 * @author:liuhang
 * @date:2016年10月8日
 * @Description:TODO
 */
package com.liuhang.springinaction.knights;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class KnightMain {

  /**
   *
   */
  public static void main(String[] args) {
    ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("knights.xml");
    Knight knight=context.getBean(Knight.class);
    knight.embarkOnQuest();
    context.close();
  }

}
