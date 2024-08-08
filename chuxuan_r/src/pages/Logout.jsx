import { redirect,useNavigate } from "react-router-dom";
import { useEffect } from "react";

export function loader(){
    localStorage.removeItem("dpj-sb");
    localStorage.removeItem("subject");
    localStorage.removeItem("branchDetail");
    localStorage.removeItem("writingRecord");
    localStorage.removeItem("user");
    localStorage.removeItem("long");
    localStorage.removeItem("token");
    localStorage.removeItem("expiration");
    return null;

}

export default function LogoutPage(){
    const navigate=useNavigate();
    useEffect(()=>{
        navigate('/');
    },[])
}