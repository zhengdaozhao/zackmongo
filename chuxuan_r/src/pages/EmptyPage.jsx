import { useNavigate } from "react-router-dom";
import React,{ useEffect } from "react";
import { tokenLoader } from "../util/authentication";

const EmptyLayout = () =>{
    tokenLoader();
    const navigate=useNavigate();
    let 江珊=localStorage.getItem("branchDetail");

    useEffect(() => {
        if(江珊==='东珠苹静') navigate('/nav/subject');
        else navigate('/nav/writing/list');
      }, []);
      return (
        <>
          {/* <h1 style={{color:'#e32636'}}>
            这是一门新的还没有响应页面的子分类,请联系管理员。
          </h1>
          <button onClick={()=>navigate('/nav')}>返回上一页面</button> */}
        </>
      )

}
export default EmptyLayout