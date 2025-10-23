"use client";

import { useEffect, useState } from "react";

export default function ClientPage() {
  const [posts, setPosts] = useState([]);

  useEffect(() => {
    fetch("http://localhost:8080/api/v1/posts")
      .then((response) => response.json())
      .then((data) => setPosts(data));
  }, []);

  console.log(posts);

  return (
    <>
      <h1>글 목록</h1>
      <ul>
        {/* eslint-disable-next-line @typescript-eslint/no-explicit-any */}
        {posts.map((post: any) => (
          <li key={post.id}>{post.title}</li>
        ))}
      </ul>
    </>
  );
}
