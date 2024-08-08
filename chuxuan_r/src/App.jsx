import React from "react";
import {
  createBrowserRouter,
  // createHashRouter,
  RouterProvider
} from 'react-router-dom';
import Nav,{loader as NavLoader} from './pages/Nav';
import EmptyLayout from "./pages/EmptyPage";
import SubjectManagement,{ action as subjectAction} from "./pages/SubjectManagement";
import WritingForm from "./form/WritingForm";
import WritingList from "./form/WritingList";
import WritingShow from "./form/WritingShow";
import LoginForm from "./pages/Login";
import SubjectEdit from "./pages/SubjectEdit";
import LogoutPage,{loader as logoutLoader} from "./pages/Logout";
import WritingEdit from "./edit_page/WritingEdit";
import SerializePage from "./pages/SerializePage";
import DeserializePage from "./pages/DeserializePage";
import RichText from "./component/RichText";

const router = createBrowserRouter([
  {
    path: '/',
      element: <LoginForm />,
      // errorElement: <ErrorPage />,
      id: 'login',
      // loader: HomeLoader,
    },
  {
    path: '/logout',
      element: <LogoutPage />,
      // errorElement: <ErrorPage />,
      id: 'logout',
      loader: logoutLoader,
  },
  {
      path: '/nav',
      element: <Nav />,
      id: 'navigation',
      loader: NavLoader,
      children: [
        { path: '/nav/manage', 
          element: <SubjectManagement />,
          // loader: subjectLoader,
          action: subjectAction
        },
            // 临时测试用
      // { path: '/nav/branch2', element: <SubjectExtLayout /> },
      { path: '/nav/empty', 
        element: <EmptyLayout />,
        // loader: tokenLoader,
       },
      { path: '/nav/writing/list', 
        element: <WritingList />,
      },
      { path: '/nav/writing/input', 
        element: <WritingForm />,
      },
      { path: '/nav/writing/detail', 
        element: <WritingShow />,
      },
      // 2024/7/1 add edit implementation
      { path: '/nav/writing/edit', 
        element: <WritingEdit />,
      },
      // 2024/7/2 add
      { path: '/nav/subject', 
        element: <SubjectEdit />,
      },
      { path: '/nav/serialize', 
        element: <SerializePage />,
      },
      { path: '/nav/deserialize', 
        element: <DeserializePage />,
      },
      { path: '/nav/richtext', 
        element: <RichText />,
      },
     ],
}
]);

function App() {
  return <RouterProvider router={router} />;
}

export default App;
