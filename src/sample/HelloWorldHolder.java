package sample;

/**
* sample/HelloWorldHolder.java .
* 由IDL-to-Java 编译器 (可移植), 版本 "3.2"生成
* 从helloworld.idl
* 2020年11月15日 星期日 下午02时59分57秒 CST
*/

public final class HelloWorldHolder implements org.omg.CORBA.portable.Streamable
{
  public sample.HelloWorld value = null;

  public HelloWorldHolder ()
  {
  }

  public HelloWorldHolder (sample.HelloWorld initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = sample.HelloWorldHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    sample.HelloWorldHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return sample.HelloWorldHelper.type ();
  }

}
