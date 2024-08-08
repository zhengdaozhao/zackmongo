import React,{useRef} from 'react';
import { Form, Input, Button, Select,Image } from 'antd';
import {useNavigate} from 'react-router-dom';
import RichText from '../component/RichText';

const { TextArea } = Input;

const WritingShow = () => {
  const navigate = useNavigate();
  const zpddyz=useRef(null);

  const cxddyz=JSON.parse(localStorage.getItem('writingRecord'));
  return (
    <>
    <Form 
      layout="vertical" 
      disabled
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
        <Input style={{color:'#a626aa'}} />
      </Form.Item>
      <Form.Item name="sample" 
        label={<label  style={{color:'blue'}}>
                详情
                </label>} 
        >
          <RichText ref={zpddyz} content={cxddyz.sample} />
      </Form.Item>
    </Form>
    <hr />
    <div style={{display:'flex',justifyContent:'center', alignItems:'center'}}>
        <Button style={{width:'8rem',marginTop:'1rem',marginBottom:'2rem'}}  type='primary' danger onClick={()=>navigate(-1)} >OK</Button>
    </div>
    </>
  );
};

export default WritingShow;
