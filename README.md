# 🅽️ Tic-Tac-Toe (X | O)

A simple **Tic-Tac-Toe** web game built using **Java (Servlets, JSP)**, **Tomcat**, **jQuery**, and **AJAX**.

## 📌 Features
- **Classic Tic-Tac-Toe gameplay** with AJAX move handling.
- **Tomcat server integration** for smooth request processing.
- **Server-side game logic** (validating moves, determining the winner, checking for a draw).
- **Dynamic interface using JSP + jQuery**.
- **REST API endpoints for game interaction**.

---

## 🚀 Getting Started

### 1️⃣ **Install Dependencies**
This project uses **Maven**. Ensure you have Java 17+ installed.

```sh
mvn clean install
```

### 2️⃣ **Run Tomcat Server**
You can start the server via the command line:
```sh
mvn tomcat7:run
```
Or use **IntelliJ IDEA** (Run → Tomcat).

### 3️⃣ **Open the Game**
Once the server is running, open:
```
http://localhost:8080/
```

---

## 🛠 Project Structure
```
📂 src/main/java/org/example/
 ├── 📂 controller    # Servlets handling requests
 ├── 📂 service       # Game logic
 ├── 📂 dto           # Data Transfer Objects (DTO)
 ├── 📂 model         # Game entities
```
```
📂 src/main/webapp/
 ├── 📂 css           # Stylesheets
 ├── 📄 index.jsp     # Game UI
```
```
📂 resources/static/
 ├── 🎨 favicon.ico   # Favicon
 ├── 🎨 draw.jpg      # Image used for a draw
```

---

## 💽 REST API (Move Handling)
### **POST** `/rest/move`
💡 **Description**: Sends a move to the server.  
📤 **Request Body (JSON)**:
```json
{
  "coordinates": "4",
  "value": "X"
}
```
📥 **Response (JSON)**:
```json
{ "status": "success" }
```

---

## 🛠 Tech Stack
✅ **Java 17+**  
✅ **Maven**  
✅ **Jakarta Servlet API**  
✅ **Tomcat 10+**  
✅ **jQuery + AJAX**  
✅ **Jackson (JSON Processing)**  
✅ **JSP (Java Server Pages)**  

---

## 📌 Future Improvements
- 🔹 Enhance animations for winning cells.
- 🔹 Allow player to choose who goes first.
- 🔹 Implement an AI opponent.
- 🔹 Implement additional animations and skins.

---

## 🤝 Author
**Yuriy Subotinov** – Developer of this project.  
💬 **Contact**: [GitHub Profile](https://github.com/CrapMind)

---

🛠 **Feel free to contribute, report issues, or suggest improvements!** 🚀

