import Link from "next/link";

function Home() {
  const handleLogout = async () => {
    try {
      const loginData = localStorage.getItem("loginData");
      if (!loginData) {
        console.error("No login data found in local storage.");
        return;
      }

      const { accessToken, refreshToken } = JSON.parse(loginData);
      if (!refreshToken) {
        console.error("No refresh token found in login data.");
        return;
      }

      const response = await fetch("http://localhost:8080/members/logout", {
        method: "DELETE",  // DELETE 대신 POST로 변경
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${accessToken}`,
        },
        body: JSON.stringify({
          refreshToken: refreshToken,
        }),
      });

      if (response.ok) {
        localStorage.removeItem("loginData");
      } else {
        console.error(response.statusText);
      }
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div>
      <h1>Hello, Next.js!</h1>
      <Link href="/about">
        <div>About</div>
      </Link>
      <br />
      <Link href="/login">
        <div>Login</div>
      </Link>
      <button onClick={handleLogout}>Logout</button>
    </div>
  );
}

export default Home;