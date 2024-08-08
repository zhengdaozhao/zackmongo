import React, { useEffect,useState,Fragment } from "react";
import {useNavigate} from 'react-router-dom';
import {
  Button,Table,
  Spin,
  notification,Popconfirm
} from 'antd';
import TableService from "../util/tableService";

const openNotificationWithIcon = (type, message, description) => notification[type]({message, description});

function WritingList() {
    const navigate = useNavigate();
    const [user, setUser]=useState([]);
    const [xiaofang,setXiaofang]=useState(false);
    const [isLoading, setIsLoading]=useState(false);
    const subject=localStorage.getItem("branchDetail");

    const deleteOneRecord = async (id)=>{
      try{
        await TableService.delOneWriting(id);
        setXiaofang(x=>!x);
        openNotificationWithIcon("success","删除记录成功");
      }catch(ex){
        openNotificationWithIcon("error","删除记录异常,请联系管理员");
      }

    }
    const editRecord = (record)=>{
      localStorage.setItem("writingRecord",JSON.stringify(record));
      navigate('/nav/writing/edit');
    }

    const columns = [
        {
        title: '标题',
        dataIndex: 'title',
        key: 'title',
        render: (text, record) => {
            
            return <a onClick={()=>{
                // localStorage.setItem("writingId",record.id);
                localStorage.setItem("writingRecord",JSON.stringify(record));
                navigate('/nav/writing/detail');
            }}>
                {text}
            </a>
          }
        },
        {
        title: '做成日',
        dataIndex: 'beginday',
        key: 'beginday',
        },
        {
          // reuse the perfect code of lagacy project fujitsu
          title: 'Action',
          className:'laoyaoziling',
          key: 'action',
          render: (text, record) => (
            <Fragment>
              <Popconfirm
                title={`删除 ${record.title}`} 
                description="你确定真的要删除吗?"
                onConfirm={() => deleteOneRecord(record.id)} okText='确定' cancelText='取消'
              >
                <Button type="primary" danger style={{fontSize:'12px'}}>删除</Button>
              </Popconfirm>

              <Button style={{marginLeft: '5px',backgroundColor:'green',fontSize:'12px'}} type='primary' onClick={() => editRecord(record)}>修改</Button>
            </Fragment>
          ),
        }          
    ];

  useEffect( ()=>{
    const zpddyz = async ()=> {
        try{
          setIsLoading(true);
          const res = await TableService.getAllWriting(subject);
          // console.log(res.data);
          setUser(res.data);
          setIsLoading(false);
        } catch(err){
          setIsLoading(false);
          openNotificationWithIcon("error","获取后台写作数据出错,请联系管理员")
        }
    };
    zpddyz();
  },[xiaofang]);

const getRowClassName = (_, index) => {
    let className = ''
    // oddRow 和 evenRow为我们css文件中的样式名称
    className = index % 2 === 0 ? "oddRow" : "evenRow"
    return className
  }

  return (
    <>
    <h1>{localStorage.getItem("branchDetail")}</h1>
    <div style={{background:'white'}}>
          {isLoading ?
          <>
            <h2>正在获取以往记录...</h2>
            <Spin style={{marginLeft:'23rem'}} size="large"/> 
          </>
          : 
          <>
            {/* <h2>光辉的足迹</h2> */}
            <Table columns={columns} 
              dataSource={user} 
              rowClassName={getRowClassName}  
              rowKey={rec=>rec.id} 
              />
          </>
          }
          <div style={{display:'flex',justifyContent:'center'}}>
            <Button style={{width:'8rem',marginTop:'1rem',marginBottom:'2rem'}}  type="primary" onClick={()=>navigate('/nav/writing/input')}>
              我要追加
            </Button>
          </div>
    </div>
    </>
  );
}

export default WritingList;