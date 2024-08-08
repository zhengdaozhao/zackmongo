import React, {useState,useEffect,useRef} from 'react';
import { Form, Input, Button, Select,Image,Checkbox,notification } from 'antd';
import {useNavigate} from 'react-router-dom';
import RichText from '../component/RichText';
import TableService from '../util/tableService';
const openNotificationWithIcon = (type, message, description) => notification[type]({message, description});

const WritingEdit = () => {
  const navigate = useNavigate();
  // const [pjddyz,setPjddyz]=useState([]);
  const mjddyz=useRef(null);

  const cxddyz=JSON.parse(localStorage.getItem('writingRecord'));
  const onFinish = (values) => {
    // Here you can handle form submission logic, e.g., send data to server
    const formData=new FormData();

    // 添加其他字段  
    formData.append('id', cxddyz.id); 
    formData.append('title', values.title);  
    formData.append('sample', mjddyz.current.richtext);  
   
    //send http request to store files & images as well as save other info into database
    async function innerMethod(data){
        try{
            await TableService.updateWritingDb(data);
            openNotificationWithIcon("success","数据更新成功!")
            navigate('/nav/writing/list')
        }catch(ex){
            openNotificationWithIcon("error","数据更新失败!")
        }
    }    
    innerMethod(formData);
  };

  return (
    <>
    <Form 
      layout="vertical"
      onFinish={onFinish}
      initialValues={{
        title:cxddyz.title,
        sample:cxddyz.sample,
      }}
      >
      <Form.Item name="title"
       label={<label  style={{color:'blue'}}>
            题目
            </label>} 
        >
        <Input />
      </Form.Item>
      <Form.Item name="sample" 
        label={<label  style={{color:'blue'}}>
                详情
                </label>} 
        >
          <RichText ref={mjddyz} content={cxddyz.sample} />
      </Form.Item>
      <hr />
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
    </Form>

    </>
  );
};

export default WritingEdit;
