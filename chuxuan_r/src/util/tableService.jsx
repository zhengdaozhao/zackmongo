import axios from "axios";
import authHeader from "./authHeader";

const API_TABLE_URL = "http://localhost:9000/table/";


const getAllWriting = (subject) => {
  return axios.get(API_TABLE_URL + "writing"+`?subject=${subject}`,{
    headers : authHeader()
  });
};

const delOneWriting = (id) => {
  return axios.post(API_TABLE_URL + "writing/delete"+`?id=${id}`,null,{
    headers : authHeader()
  });
};
// 2024/7/1 add 
const updateWritingDb = (formData) => {
  return axios.post(API_TABLE_URL + "writing/update",formData,{
    headers : authHeader()
  });
};

const TableService = {
    getAllWriting,
    delOneWriting,
    updateWritingDb,
};

export default TableService;