# 🏥 Daisuke Clinic - Sistem Manajemen Klinik Berbasis Java

> *Pernahkah kamu membayangkan bagaimana sebuah rumah sakit atau klinik mengelola ratusan data pasien, jadwal dokter, serta janji temu setiap harinya? Di balik layar, ada sistem yang memastikan semuanya berjalan rapi dan efisien. Tapi, seperti apa bentuk sistem itu sebenarnya?*

**Daisuke Clinic** adalah aplikasi Java yang dirancang untuk mensimulasikan proses manajemen klinik sederhana. Proyek ini menggabungkan konsep-konsep pemrograman berorientasi objek dan struktur data seperti **Linked List**, **Queue**, dan **Binary Search Tree (BST)** untuk mendemonstrasikan bagaimana data pasien, dokter, dan janji temu dapat dikelola secara terstruktur.

---

## 📁 Struktur Folder

```
├── src/
│ ├── com/daisukeclinic/
│ ├── com/daisukeclinic/model/
│ └── com/daisukeclinic/structure/
├── bin/
├── patients.txt
├── doctors.txt
└── README.md
```


---

## 🧩 Fitur Utama

- ✅ Manajemen **pasien** menggunakan struktur **Linked List**
- ✅ Manajemen **dokter** dengan **Nested Linked List**
- ✅ Sistem **login dokter**
- ✅ Penjadwalan **janji temu pasien** menggunakan **Queue**
- ✅ Pencarian dan pengurutan pasien melalui **Binary Search Tree (BST)**
- ✅ Penyimpanan data secara eksternal ke file `.txt`

---

## 🧪 Fitur Program

1. Add New Patient  
2. Remove Patient by ID  
3. Search Patient by Name  
4. Display All Patients  
5. Doctor Login  
6. Doctor Logout  
7. View Last Logged-in Doctor  
8. Schedule Appointment  
9. Process Appointment  
10. Display Upcoming Appointments  
11. Search Patient by ID (BST)  
12. Display All Patients (BST Inorder)
13. Add Medical History
14. Register new doctor

---

## 🧠 Langkah Alur Algoritma Program

Dalam sistem simulasi klinik ini, pengguna berinteraksi melalui antarmuka berbasis teks. Proses kerja program dibangun atas serangkaian algoritma berikut:

1. **Menambahkan Pasien**  
   Pengguna memasukkan ID dan nama pasien. Program membuat node baru dan menambahkannya ke `LinkedList`. Data juga disimpan secara permanen ke file `patients.txt`.

2. **Menghapus Pasien berdasarkan ID**  
   Program menelusuri linked list dan menghapus node yang memiliki ID sesuai input.

3. **Mencari Pasien berdasarkan Nama**  
   Program menelusuri tiap node dalam linked list dan mencocokkan nama pasien.

4. **Login & Logout Dokter**  
   Dokter memasukkan username dan password. Jika cocok, data dokter disimpan ke variabel `currentDoctor`. Logout menghapus nilai variabel ini.

5. **Menjadwalkan Janji Temu**  
   Saat dokter login, mereka dapat menambahkan janji temu pasien ke `Queue`.

6. **Memproses Janji Temu**  
   Antrian diproses berdasarkan urutan masuk menggunakan struktur data `Queue`.

7. **Pencarian Pasien dengan Binary Search Tree**  
   Program menyalin semua pasien dari linked list ke struktur `BST`. Pencarian berdasarkan ID dilakukan secara efisien. Inorder traversal digunakan untuk menampilkan pasien terurut.

---

## ⚙️ Spesifikasi Teknis
Bahasa Pemrograman : Java
Struktur Data :
- LinkedList (manajemen pasien)
- Queue (sistem antrian janji temu)
- Binary Search Tree (pencarian dan pengurutan pasien)
- File Persistensi : patients.txt, doctors.txt
- Antarmuka : CLI (Command Line Interface)


---

## 🔍 Implementasi

### 1. 🔗 Struktur Node Pasien (LinkedList)

```
class PatientNode {
    String id;
    String name;
    PatientNode next;

    public PatientNode(String id, String name) {
        this.id = id;
        this.name = name;
        this.next = null;
    }
}
```
Pada method constructor, argumen id dan name digunakan untuk mengisi data pasien. Properti next diinisialisasikan null, menandakan bahwa node ini belum terhubung ke node lain. Struktur ini membentuk rantai pasien (linked list) untuk mempermudah traversal dan manajemen data.

---

### 2. 🔍 Pencarian Pasien Berdasarkan Nama

```
public PatientNode searchByName(String name) {
    PatientNode current = head;
    while (current != null) {
        if (current.name.equalsIgnoreCase(name)) {
            return current;
        }
        current = current.next;
    }
    return null;
}
```
Fungsi ini menggunakan pencarian linear dalam <i>linked list</i>. current akan bergerak dari head hingga menemukan node dengan nama yang sesuai (mengabaikan huruf kapital). Jika tidak ditemukan, akan dikembalikan null.

---

### 3. 🧑‍⚕️ Login Dokter & Manajemen Sesi

```
Doctor currentDoctor = null;

public boolean login(String username, String password) {
    for (Doctor d : doctors) {
        if (d.username.equals(username) && d.password.equals(password)) {
            currentDoctor = d;
            return true;
        }
    }
    return false;
}
```
Setelah input, sistem mencocokkan username dan password dengan data dokter yang tersedia. Jika cocok, objek Doctor yang login akan disimpan dalam currentDoctor untuk keperluan akses berikutnya (menjadwalkan janji, melihat pasien, dll).

---

### 4. 🗓️ Menjadwalkan Janji Temu (Queue)

```
Queue<Appointment> appointmentQueue = new LinkedList<>();

public void scheduleAppointment(Appointment a) {
    appointmentQueue.add(a);
}
```
Struktur data <i>Queue</i> digunakan untuk mengatur janji temu agar sesuai urutan masuk. Objek Appointment dimasukkan ke antrian dengan add() dan diproses secara FIFO (First In First Out).

---

### 5. 🌳 Pencarian Pasien Menggunakan BST

```
class BSTNode {
    String patientID;
    Patient data;
    BSTNode left, right;

    BSTNode(Patient data) {
        this.data = data;
        this.patientID = data.id;
        left = right = null;
    }
}
```
Struktur BSTNode menyimpan data pasien berdasarkan ID sebagai kunci utama. Properti left dan right digunakan untuk menghubungkan node-node dalam bentuk pohon biner terurut.
Saat inorder traversal dipanggil, program menampilkan pasien secara urut berdasarkan ID.

---

### 6. 🔁 Inorder Traversal BST

```
public void inorderTraversal(BSTNode node) {
    if (node != null) {
        inorderTraversal(node.left);
        System.out.println(node.data.name + " (" + node.data.id + ")");
        inorderTraversal(node.right);
    }
}
```
Inorder traversal (Left - Root - Right) menjamin bahwa data ditampilkan dalam urutan ID yang terurut (dari kecil ke besar), sesuai karakteristik BST. Cocok untuk laporan data pasien secara sistematis.

---

### 7. 💾 Menyimpan Data Pasien ke File

```
BufferedWriter writer = new BufferedWriter(new FileWriter("patients.txt"));
while (current != null) {
    writer.write(current.id + "," + current.name);
    writer.newLine();
    current = current.next;
}
writer.close();
```
Setiap data pasien dari LinkedList disimpan ke file patients.txt. Hal ini memungkinkan data tetap tersimpan meskipun program dimatikan, mendukung fitur persistensi data. Begitu juga dengan integrasi yang terdaftar dengan data dokter ke file.

---

## 🚀 Cara Menjalankan

1. Clone atau download repositori ini
2. Buka proyek menggunakan Visual Studio Code atau IDE Java lainnya
3. Pastikan Java Development Kit (JDK 8+) telah terinstal
4. Jalankan file `Main.java` yang terletak di `src/com/daisukeclinic/`

---

## 📃 Langkah-langkah Berjalannya Program
<p align="center" width="100%">
    <img width="50%" src="https://github.com/user-attachments/assets/0582c461-cdf7-4ea9-88dc-198003b286bb">
</p>
Memasuki antarmuka awal, kita dihadapkan dengan opsi-opsi kita mau masuk sebagai pasien, dokter, atau admin.


### 🙍‍♂️ Masuk Sebagai Pasien
<p align="center" width="100%">
    <img width="50%" src="https://github.com/user-attachments/assets/97c61d4c-f684-4a68-b76a-df8e8e24463d">
</p>
Di sini, kita bisa melakukan perubahan dan pencarian terhadap data pasien, di antaranya kita bisa

1. Menambah Pasien Baru
2. Mencari Pasien Berdasarkan Nama
3. Mengajukan Pertemuan dengan dokter
4. Mencari ID Pasien


<br/><br/>
<p align="center" width="100%">
    <img width="50%" src="https://github.com/user-attachments/assets/ca527a77-d1a4-4e9f-8c57-66572f1a0644">
</p>
Ketika kita memilih opsi 1, yaitu Menambah Pasien Baru, kita akan diminta memasukkan ID, Nama, Umur, Alamat, dan Nomor Telepon dari Pasien.

<br/><br/>
<p align="center" width="100%">
    <img src="https://github.com/user-attachments/assets/453c0ce0-59fe-418f-9a9b-7cf1f902d267">
</p>
Ketika kita memilih opsi 2, yaitu Mencari Pasien Berdasarkan Nama, kita akan diminta memasukkan nama, lalu program akan memunculkan ID, Nama, Umur, Alamat, Nomor Telepon, dan Riwayat Medis.

<br/><br/>
<p align="center" width="100%">
    <img width="50%" src="https://github.com/user-attachments/assets/b8931f07-8f00-43e5-9753-4ef892eb8e57">
</p>
Ketika kita memilih opsi 3, tetapi dokter belum <i>login</i>, maka tidak bisa membuat pertemuan kepada dokter.

<br/><br/>
<p align="center" width="100%">
    <img width="50%" src="https://github.com/user-attachments/assets/787070a1-8378-405a-84be-9c48ba765683">
</p>
Ketika kita memilih opsi 3 dan dokter sudah <i>login</i>, maka perjanjian akan di-data. Masukkan ID Dokter yang dipilih, ID Pasien, dan Pukul Pertemuan.

<br/><br/>
<p align="center" width="100%">
    <img src="https://github.com/user-attachments/assets/4db741e6-2a49-46a0-91e3-d251ac08608c">
</p>
Ketika kita memilih opsi 4, kita diminta untuk memasukan ID Pasien untuk mencarinya. Setelah dimasukkan, program akan menampilkan detail dari pasien yang dicari.

### 🧑‍⚕️Masuk Sebagai Dokter
<p align="center" width="100%">
    <img width="50%" src="https://github.com/user-attachments/assets/750a5c20-3b4b-4304-8dda-098405f6584d">
</p>
Saat memilih opsi Masuk Sebagai Dokter, harus memasukkan PIN untuk mengautentikasi bahwa yang masuk adalah dokter.

<br/><br/>
<p align="center" width="100%">
    <img width="50%" src="https://github.com/user-attachments/assets/fa37113a-42b6-430b-9feb-c12cd7a5cbba">
</p>
Di sini, kita bisa melakukan penambahan dokter baru serta media masuk dan keluarnya untuk keaktifan dokter.

1. Registrasi Dokter Baru
2. <i>Login</i> Dokter
3. <i>Logout</i> Dokter

<br/><br/>
<p align="center" width="100%">
    <img width="50%" src="https://github.com/user-attachments/assets/81b43eab-5deb-4262-84e5-ae6b4d6bcaf3">
</p>
Ketika memilih opsi 1, kita akan diminta memasukan ID, Nama, Bidang Spesialis dari Dokter, dan <i>Shift</i> Kerjaannya.

<br/><br/>
<p align="center" width="100%">
    <img width="50%" src="https://github.com/user-attachments/assets/cb0c5522-8730-47e1-a13d-f1e903837897">
</p>
<p align="center" width="100%">
    <img width="50%" src="https://github.com/user-attachments/assets/b197944a-20a8-41d8-8221-1da88ce25365">
</p>
Setelah ditambahkan, maka akan ditunjukkan dokter-dokter yang sudah terdaftar, beserta ID, Spesialis, dan <i>Shift</i> Kerjaannya, sekaligus memberitahu bahwa dokter yang baru ditambahkan sudah berhasil ditambahkan.

<br/><br/>
<p align="center" width="100%">
    <img width="50%" src="https://github.com/user-attachments/assets/2aa1e7a9-e589-4e2c-949a-f7aa6f23166d">
</p>
Ketika kita memilih opsi 2, maka kita diminta memasukkan ID Dokter mana yang mau di-login-kan. Setelah <i>login</i>, artinya dokter itu sudah siap untuk berkerja dan dimintai janji temu.

<br/><br/>
<p align="center" width="100%">
    <img width="50%" src="https://github.com/user-attachments/assets/4a1397bb-f4d7-490b-aa78-37f6a499f4c8">
</p>
Ketika kita memilih opsi 3, maka kita diminta memasukkan ID Dokter mana yang mau di-logout-kan. Setelah <i>logout</i>, artinya dokter itu sudah pulang atau sudah tidak pada <i>shift</i> kerjanya.

### 👨‍💻 Masuk Sebagai Admin
<p align="center" width="100%">
    <img width="50%" src="https://github.com/user-attachments/assets/18d3b997-5fbe-438a-ae19-44f1bdea98d4">
</p>
Saat memilih opsi Masuk Sebagai Admin, harus memasukkan PIN untuk mengautentikasi bahwa yang masuk adalah admin

<br/><br/>
<p align="center" width="100%">
    <img width="50%" src="https://github.com/user-attachments/assets/91ffe018-5083-4346-865e-01f2bec02861">
</p>
Di sini, kita bisa melakukan perubahan dan pencarian terhadap data pasien, dokter, dan pertemuan, di antaranya kita bisa

1. Menghapus Pasien dengan Menggunakan ID
2. Memperlihatkan Semua Daftar Pasien
3. Memperlihatkan Dokter Yang Baru Saja <i>Login</i>
4. Memproses Pertemuan atau Janji Temu
5. Memperlihatkan Pertemuan atau Janji Temu Mendatang
6. Memperlihatkan Semua Daftar Pasien Berdasarkan BST <i>Inorder</i>
7. Menambahkan Riwayat Medis kepada Pasien

<br/><br/>
<p align="center" width="100%">
    <img width="50%" src="https://github.com/user-attachments/assets/c6898dee-35d0-4454-82a5-c942ae47c506">
</p>
Ketika memilih opsi 1, masukkan ID Pasien yang mau dihapus dari daftar pasien.

<br/><br/>
<p align="center" width="100%">
    <img src="https://github.com/user-attachments/assets/3aa4d480-c6b4-4d49-a58a-b71bbc936b45">
</p>
Ketika memilih opsi 2, kita akan diperlihatkan semua daftar pasien beserta ID, Alamat, Nomor Telepon, dan Riwayat Medisnya.

<br/><br/>
<p align="center" width="100%">
    <img width="50%" src="https://github.com/user-attachments/assets/cb864400-2b30-4296-a325-c1c3a71b6eb5">
</p>
Ketika memilih opsi 3, kita akan diperlihatkan dokter yang baru saja login, dengan detail ID, Nama, Spesialis, <i>Shift</i> Kerja, dan Pertemuan Yang Dimiliki.

<br/><br/>
<p align="center" width="100%">
    <img width="50%" src="https://github.com/user-attachments/assets/74682d3e-5915-4071-9808-9b7aca3d01d0">
</p>
Ketika memilih opsi 4, kita akan memproses pertemuan yang diminta oleh pasien dan meneruskannya ke dokter supaya bisa melakukan pertemuan.

<br/><br/>
<p align="center" width="100%">
    <img width="50%" src="https://github.com/user-attachments/assets/3685e5c7-c5a1-4bfd-ba07-0c16969caec2">
</p>
Ketika memilih opsi 5, akan ditunjukkan pertemuan mendatang. Karena kita sudah memproses pertemuan mendatang milik dr. Quanrui, maka sudah tidak ada pertemuan mendatang yang diminta oleh pasien.

<br/><br/>
<p align="center" width="100%">
    <img width="50%" src="https://github.com/user-attachments/assets/d456e7d7-7ec3-4375-ba33-d980563dbbe8">
</p>
Ketika memilih opsi 6, kita akan diperlihatkan semua daftar pasien secara BST <i>Inorder</i> beserta ID, Alamat, Nomor Telepon, dan Riwayat Medisnya.

<br/><br/>
<p align="center" width="100%">
    <img width="50%" src="https://github.com/user-attachments/assets/07296705-366c-4c9a-baa2-f5011a4b5006">
</p>
Ketika memilih opsi 7, kita bisa menambahkan riwayat medis ke pasien dengan mencantumkan ID Pasien dulu baru menuliskan apa riwayat medisnya.

### 🚪 Laman Akhir
<p align="center" width="100%">
    <img width="50%" src="https://github.com/user-attachments/assets/b53684b3-a512-473e-8212-598b975e1f4e">
</p>
Di laman keluar, akan ada ucapan terima kasih telah menggunakan programnya, lalu akan dikeluarkan dari program, dengan maksud pengguna tidak bisa memasukkan apa-apa lagi sebagai input.

---
## ⚙️ Teknologi dan Konsep yang Digunakan

- 💻 Java 8+
- 🧱 OOP (Object-Oriented Programming)
- 🧮 Struktur Data:
  - Linked List (Pasien, Dokter)
  - Queue (Janji Temu)
  - Binary Search Tree (Pencarian Pasien)
- 🗂️ File I/O untuk penyimpanan data

---

## 👨‍💻 Kontribusi

1) Baihaqi Hakim Abdullah (L0124043) - Membuat Program Awal dan Akhir (Antarmuka) 
2) Daffa Dewanda Putra (@daffadewanda - L0124094) - Membuat File README 
3) Fandhi Ahmad Husen (@fafaalghozali - L0124052) - Membuat Program Integratif ke data `.txt`
4) Ariwata Alfajri (L0124088)
5) M. Arva Zaky (@ArdovZ - L0122104)

---
<p align="center" width="100%">
   Kelompok 9, Kelas C, Semester 2 Tahun Ajaran 2024/2025 <br/> Program Studi S-1 Informatika, Universitas Sebelas Maret
</p>

