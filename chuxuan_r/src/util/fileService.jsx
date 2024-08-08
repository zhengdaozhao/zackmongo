import axios from "axios";
import authHeader from "./authHeader";

const API_FILE_URL = "http://localhost:9000/localupload/";

const uploadFileAndSaveToWritingDb = (updData) => {
    return axios.post(API_FILE_URL + "baiduwenxin/writing",updData,{
    headers : authHeader()
  }
)};

const FileService = {
  uploadFileAndSaveToWritingDb,
};

export default FileService;