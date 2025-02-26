---

# 🎬 MVVM-DAMD: Aplicación de Películas con TMDB, Firebase y Room

![Android](https://img.shields.io/badge/Android-12-green?style=flat&logo=android)
![Kotlin](https://img.shields.io/badge/Kotlin-2.1.0-blueviolet?style=flat&logo=kotlin)
![Firebase](https://img.shields.io/badge/Firebase-Auth%20%7C%20Firestore-yellow?style=flat&logo=firebase)

## Descripción
Aplicación de Android desarrollada en **Kotlin** utilizando la arquitectura **MVVM** con las siguientes características:
- 🔹 **Autenticación de usuarios** con **Firebase Authentication**.
- 🔹 **Sincronización de datos** con **Firestore** y almacenamiento local con **Room**.
- 🔹 **Consumo de la API de TheMovieDB** para obtener listas de películas populares, mejor valoradas, en cartelera y próximas.
- 🔹 **Inyección de dependencias** con **Dagger Hilt**.
- 🔹 **Gestión de favoritos** guardando películas en **Room**.
- 🔹 **Interfaz moderna con Material Design y Navigation Drawer**.

---

## **Tecnologías y Librerías Utilizadas**
| Tecnología | Uso |
|------------|--------------------------|
| **Kotlin** | Lenguaje de programación principal |
| **MVVM** | Patrón de arquitectura para la separación de capas |
| **Dagger Hilt** | Inyección de dependencias |
| **Retrofit & Gson** | Consumo de API REST |
| **Room** | Base de datos local SQLite |
| **Firebase Authentication** | Registro e inicio de sesión de usuarios |
| **Firestore** | Almacenamiento en la nube |
| **Picasso** | Carga de imágenes |
| **Navigation Drawer** | Menú lateral de navegación |

---

## **Instalación y Configuración**
### **1️⃣ Clonar el repositorio**
```bash
git clone https://github.com/kodebidean/PMDM_U5_MVVM_Actividad_Final.git
cd PMDM_U5_MVVM_Actividad_Final
```

### **2️⃣ Configurar Firebase**
- **Agrega el archivo `google-services.json` en `app/`** (Este archivo no se sube por seguridad).
- **Habilita Firebase Authentication y Firestore en la consola de Firebase**.

### **3️⃣ Agregar clave API de TMDB**
- **Obtén tu API Key en** [TheMovieDB](https://www.themoviedb.org/)
- **Crea un archivo en `res/values/secrets.xml`**
```xml
<resources>
    <string name="tmdb_api_key">TU_API_KEY</string>
</resources>
```

### **4️⃣ Ejecutar la aplicación**
```bash
./gradlew build
```
O desde **Android Studio** -> **Run App (Shift + F10)**

---

## 📂 **Estructura del Proyecto**
```plaintext
📂 mvvm-damd
 ├── 📁 app
 │   ├── 📁 src/main/java/com/kodeleku/mvvm_damd
 │   │   ├── 📁 data  # Capa de datos (Firebase, Room, API)
 │   │   │   ├── 📁 local   # Base de datos local (Room)
 │   │   │   ├── 📁 repository  # Repositorios (Firestore, API)
 │   │   ├── 📁 ui  # Interfaz gráfica
 │   │   │   ├── 📁 view  # Activities & Fragments
 │   │   │   ├── 📁 adapter  # RecyclerView Adapters
 │   │   │   ├── 📁 viewmodel  # ViewModels (MVVM)
 │   │   ├── 📁 di  # Inyección de dependencias (Dagger Hilt)
 │   │   ├── MyApplication.kt  # Clase de aplicación
 │   ├── 📁 res/values
 │   │   ├── 📄 strings.xml  # Texto de la app
 │   │   ├── 📄 secrets.xml  # API Keys (excluido en `.gitignore`)
 │   ├── 📄 AndroidManifest.xml  # Configuración de la app
```

---

## **Funcionalidades**
- **Inicio de sesión con Firebase**  
- **Consumo de la API de TMDB con Retrofit**  
- **Gestión de películas favoritas con Room**  
- **Menú lateral con Navigation Drawer**  
- **Interfaz moderna con Material Design**

---

## **Licencia**
Este proyecto es de código abierto bajo la licencia **MIT**.

---

## **Desarrollado por**
**ImaMultidev** - [MiPortfolio](https://imamultidev.dev/)

<div align="center">
  <h2>📫 Conecta conmigo</h2>
  <table>
    <tbody>
      <tr>
        <td>
          <a href="https://www.linkedin.com/in/imanol-mugueta-unsain-b18ba92b3/"  target="_blank" rel="noopener noreferrer">
            <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/linkedin/linkedin-original.svg"  target="_blank" rel="noopener noreferrer" width="50" height="50" alt="LinkedIn"/>
          </a>
        </td>
        <td>
          <a href="https://discord.com/users/tu-discord-id">
            <img src="https://discord.com/assets/2c21aeda16de354ba5334551a883b481.png"  target="_blank" rel="noopener noreferrer" width="50" height="50" alt="Discord"/>
          </a>
        </td>
        <td>
          <a href="mailto:contact@imamultidev.dev">
            <img src="https://cdn-icons-png.flaticon.com/512/732/732200.png" width="50" height="50" alt="Email Profesional"/>
          </a>
        </td>
      </tr>
    </tbody>
  </table>
</div>
---