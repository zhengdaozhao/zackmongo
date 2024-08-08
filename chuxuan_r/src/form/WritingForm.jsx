import React, { useRef } from 'react';
import { Form, Input, Button, notification,Select } from 'antd';
import RichText from '../component/RichText';
import {useNavigate} from 'react-router-dom';
import FileService from '../util/fileService';

const openNotificationWithIcon = (type, message, description) => notification[type]({message, description});
const { TextArea } = Input;

const WritingForm = () => {
  const [form] = Form.useForm();
  const cxddyz=useRef(null);
  const navigate = useNavigate();

  // Form submit handler
  const onFinish = (values) => {
    // Here you can handle form submission logic, e.g., send data to server
    const formData=new FormData();
    formData.append('title', values.title);  
    formData.append('sample', cxddyz.current.richtext);  
    formData.append('subject', localStorage.getItem("branchDetail"));
   
    //send http request to store files & images as well as save other info into database
    async function innerMethod(data){
        try{
            await FileService.uploadFileAndSaveToWritingDb(data);
            openNotificationWithIcon("success","上传成功!")
            navigate('/nav/writing/list')
        }catch(ex){
            openNotificationWithIcon("error","上传失败!")
        }
    }    
    innerMethod(formData);
  };

  return (
    <Form form={form} 
      layout="vertical" 
      onFinish={onFinish}
      scrollToFirstError
      >
      <Form.Item name="title"
       label={<label  style={{color:'blue'}}>
            题目
            </label>} 
        rules={[{ required: true, message: '请输入标题' }]}>
        <Input placeholder='输入标题' style={{ width: '30%' }} />
      </Form.Item>
      <Form.Item name="sample" 
        label={<label  style={{color:'blue'}}>
                详情
                </label>} 
        >
        <RichText ref={cxddyz} />
      </Form.Item>
      <hr/>
      <Form.Item>
      <div style={{display:'flex',justifyContent:'center',paddingTop:'2em'}}>
        <Button type="primary" danger htmlType="submit" style={{ fontSize:'18px',width: '30%' }}>
            提交
          </Button>
        <Button type="primary"
          style={{ fontSize:'18px',width: '30%' ,marginLeft:'1em'}}
          onClick={()=>navigate(-1)}
          >
            取消
          </Button>
      </div>
      </Form.Item>
    </Form>
  );
};

export default WritingForm;
