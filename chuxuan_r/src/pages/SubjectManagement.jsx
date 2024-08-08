import React, { useState,useEffect } from "react";
import { useLoaderData,Form,redirect} from "react-router-dom";
import MenuService from "../util/menuService";
import { notification } from "antd";

const openNotificationWithIcon = (type, message, description) => notification[type]({message, description});

export async function action({ request }) {
    const formData=await request.formData();
    const updates = Object.fromEntries(formData);
    const subject = localStorage.getItem('subject');
    if (Object.keys(updates).length < 1) {
        openNotificationWithIcon("warning","你没有选择任何子分类!主题会从左侧菜单移除！")
    }
    let arr=[];
    for(let obj in updates){
        arr.push({
            key:subject+obj,
            // key:updates['subject']+obj,
            label:updates[obj]
        })
    }
    const requestData={
        subject:subject,
        allsub:JSON.stringify(arr),
    }
    try{
        await MenuService.updateOneInitDson(requestData);
        openNotificationWithIcon("success",subject+" 设定成功!")
        return redirect("/nav/");
    }catch{
        openNotificationWithIcon("error","科目管理后台更新失败!请联系管理员")
        return null;
    }
}


export default function SubjectManagement() {
    const [subjects,setSubject]=useState([]);
    const [branches,setBranches]=useState([]);
    const [xiaoguo,setXiaoguo]=useState([]);
    const [nosubmit,setNoSubmit]=useState(true);
    const [zpddyz,setZpddyz]=useState(false);
    const [isSubChecked,setIsSubChecked]=useState(false);
    const [arrzpd,setArrzpd]=useState([]);

    useEffect( ()=>{
        async function zpd(){
            try{
                const restData = await MenuService.getAllSubjects();
                const zpddyz = await restData.data;
                setSubject(zpddyz);
            }catch(ex){
                openNotificationWithIcon('error','后台获取主题信息失败，请检查后台是否启动，或者联系管理员');
            }
        }
        zpd();
    },[]);

    const handleCheckbox = (value) => {
        // console.log("=SubjectManagment=checkbox is:",value);
        setNoSubmit(false);
    }
    const handleRadioSelect = (value) => {
        async function httpRequestForBranchs(val) {
            try{
                const guoaili=await MenuService.getOneInitDson(val);
                try{
                    const resBranchs=await MenuService.getAllBranches();
                    let zhongguo=await resBranchs.data;
                    let abc=null;
                    if (guoaili.data) abc=await JSON.parse(guoaili.data.allsub);
                    if (abc){
                        let arrZpdd=[];
                        abc.forEach(aili=>{arrZpdd.push(aili.label)});
                        let arrLmj=[];
                        zhongguo.forEach(item=>{
                            if(!arrZpdd.includes(item.chname)){
                                arrLmj.push(item);
                            }
                        })
                        //xiaoguo will display at branch section
                        setXiaoguo(arrLmj);
                        setBranches(abc);
                        setArrzpd(abc.map((zpd)=>{
                            return {
                                name:zpd.label,
                                value:true
                            }
                        })
                        )
                    }else{
                        setXiaoguo(zhongguo);
                        // setBranches(zhongguo);
                    }
                    setIsSubChecked(true);
                }
                catch (ex) {
                    openNotificationWithIcon("error","子分类查询异常!请联系管理员")
                }
            }
            catch (ex) {
                openNotificationWithIcon("error","查询主题表异常!请联系管理员")
            }
        }
        httpRequestForBranchs(value);
        localStorage.setItem('subject',value);
        setZpddyz(true);
}
    return (
        <>
            {/* <h1>==========   梅花香自苦寒来   ==========</h1> */}
            <h1 style={{color:'red'}}>
                主题增减管理
            </h1>
            <h2 style={{color:'#537b35'}}>主题 (请选择要添加的主题,每次只能设定一门)</h2>
            {subjects.length ? (
              <Form method="post" >
                  <ul style={{listStyle:'none'}}>
                    {subjects.map(sub=> (
                        <li key={sub.chname} >
                            <input 
                                type="radio"
                                // at last,2024/6/25, the only correct disable way is here!
                                disabled={zpddyz}
                                id={sub.chname}
                                name="subject"
                                value={sub.chname}
                                onChange={e=>handleRadioSelect(e.target.value)}
                                 />
                            <label htmlFor={sub.chname}>
                                {sub.chname}
                            </label>
                        </li>
                    ))}
                  </ul>
                  {isSubChecked && (
                  <div>
                  <hr />
                  <h2 style={{color:'#537b35'}}>子分类</h2>
                  <h3 style={{color:'#0389ff'}}>已经选过的子分类</h3>
                  {branches.length ? (
                    <div>
                        <ul style={{listStyle:'none'}}>
                          {branches.map( (sub,idx) => (
                            <li key={sub.label}>
                                <input 
                                    type="checkbox"
                                    id={sub.label}
                                    name={sub.label}
                                    value={sub.label}
                                    checked={arrzpd[idx]}
                                    onChange={(e)=>{
                                        setArrzpd((oldzpd)=>{
                                            const newzpd=[...oldzpd];
                                            for(let i=0;i<newzpd.length;i++){
                                                if(newzpd[i].name===e.target.value){
                                                    newzpd[i].value=!newzpd[i].value;
                                                }
                                            }
                                            return newzpd;
                                        });
                                        setNoSubmit(false);
                                      }
                                    }
                                    />
                                <label  htmlFor={sub.label}>
                                    {sub.label}
                                </label>
                            </li>
                          ))}
                        </ul>
                        <hr/>
                    </div>
                ) : (
                    <div>
                        <label style={{color:'red'}}>尚未添加任何子分类</label>
                        <hr />
                    </div>
                )
                }
                <h3 style={{color:'#0389ff'}}>还没有选过的子分类 (请勾选所有想添加的分类)</h3>
                {xiaoguo.length ? 
                    (
                    <ul style={{listStyle:'none'}}>
                        {xiaoguo.map( sub => (
                            <li key={sub.chname}>
                                <input 
                                    type="checkbox"
                                    id={sub.chname}
                                    name={sub.chname}
                                    value={sub.chname}
                                    onChange={e=>handleCheckbox(e.target.value)}
                                    />
                                <label  htmlFor={sub.chname}>
                                    {sub.chname}
                                </label>
                            </li>
                        )
                        )}
                    </ul>
                    )
                : 
                    (<p style={{color:'red'}}>没有尚未添加的子类型了</p>)
                }
                <button type="submit" disabled={nosubmit} >提交</button>
                </div>
                )}
              </Form>

            ) : (
                <p style={{color:'red'}}>
                    数据库中没有任何初始化用数据,请联系管理员。
                </p> )
            }
        </>
    );
}