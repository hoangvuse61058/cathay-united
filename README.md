📦 Cathay United API Service (Spring Boot & PostgreSQL)

Dự án này là một dịch vụ API được xây dựng bằng **Spring Boot**, sử dụng **PostgreSQL** làm cơ sở dữ liệu và được đóng gói hoàn chỉnh bằng **Docker Compose** để dễ dàng triển khai.

## 🛠 Yêu cầu hệ thống

Trước khi khởi chạy, đảm bảo bạn đã cài đặt các công cụ sau:

* **Docker & Docker Compose**

* **JDK 17** hoặc **JDK 21**

* **Maven** ($\ge 3.6.3$)

## 🚀 Khởi chạy ứng dụng

Sử dụng Docker Compose là cách đơn giản nhất để build và chạy ứng dụng cùng với cơ sở dữ liệu.

### 1. Build và Chạy

Thực thi lệnh sau tại thư mục gốc của dự án:

docker-compose up -d


Lệnh này sẽ thực hiện các bước sau:

* Build image Docker cho ứng dụng **Spring Boot**.

* Khởi động hai container: **`springboot-app`** (ứng dụng) và **`postgres-db`** (cơ sở dữ liệu).

* Tự động kết nối hai container này qua mạng nội bộ Docker.

### 2. Dừng ứng dụng

Để dừng các container đang chạy:

docker-compose down


Để dừng và xóa cả **volume** (dữ liệu PostgreSQL sẽ bị mất):

docker-compose down -v


## 🌐 Kiểm tra API và Cấu hình

### Kiểm tra Swagger UI

Sau khi các container chạy thành công, bạn có thể truy cập giao diện **Swagger UI** để thử nghiệm các API:

$$
\text{http://localhost:8080/swagger-ui/index.html}
$$

> **Ghi chú**: Swagger UI yêu cầu thư viện `springdoc-openapi-starter-webmvc-ui` phiên bản $\ge 2.8.9$ để tương thích tốt với Spring Boot $3.5.x$.

### Cấu hình Kết nối Cơ sở dữ liệu

Ứng dụng kết nối tới PostgreSQL với các thông tin cấu hình mặc định sau (thường được đặt trong file `application.yml` hoặc `application.properties`):

| Thuộc tính | Giá trị | Ghi chú | 
 | ----- | ----- | ----- | 
| **Host** | `postgres` | Tên service trong `docker-compose.yml` | 
| **Port** | `5432` |  | 
| **Database** | `postgres` |  | 
| **Username** | `user` |  | 
| **Password** | `user123` |  | 
| **Schema** | `currency` | (Nếu có sử dụng) | 

> **Lưu ý**: Nếu gặp lỗi kết nối DB, hãy kiểm tra lại **hostname** (`postgres`) và đảm bảo container **`postgres-db`** đang hoạt động bình thường.

## 📁 Cấu trúc thư mục
Cấu trúc của dự án tuân theo quy ước Maven và kiến trúc phân lớp của Spring Boot, nằm chủ yếu trong thư mục `src/main/java/com/cathay_united_practice`:

### Mã nguồn (`src/main/java`)

| Thư mục | Chức năng Chính | Các tệp ví dụ |
| :--- | :--- | :--- |
| **controller** | Xử lý các yêu cầu **HTTP** và định tuyến API. | `CurrencyController.java`, `ExchangeRateController.java` |
| **service** | Chứa **Business Logic** cốt lõi. | `CurrencyService.java`, `ExchangeRateSyncService.java` |
| **repository** | Lớp giao diện để truy cập **Cơ sở dữ liệu**. | `CurrencyRepository.java`, `ExchangeRateRepository.java` |
| **model/dto** | **Data Transfer Objects (DTOs)** cho Request/Response. | `CurrencyRequest.java`, `ExchangeRateResponse.java` |
| **mapper** | Lớp/Interface ánh xạ dữ liệu (ví dụ: Entity $\leftrightarrow$ DTO). | `CurrencyMapper.java`, `Currency.java` |
| **client** | Lớp để gọi và tích hợp với **Dịch vụ bên ngoài**. | `HondaClient.java` (Ví dụ) |
| **config** | Cấu hình ứng dụng, bao gồm **Quốc tế hóa (I18N)**. | `InternationalizationConfig.java` |
| **exception** | Xử lý ngoại lệ tùy chỉnh và toàn cục. | `GlobalExceptionHandler.java` |

### Tài nguyên (`src/main/resources`)

| Thư mục/Tệp | Chức năng |
| :--- | :--- |
| `application.properties` | **Cấu hình chính** của ứng dụng Spring Boot. |
| `messages/Messages*.properties` | **Resource Bundle** hỗ trợ đa ngôn ngữ (I18N). |
| `db` | Chứa các kịch bản khởi tạo/di chuyển cơ sở dữ liệu. |

### Kiểm thử (`src/test/java`)

| Thư mục | Chức năng |
| :--- | :--- |
| `com/cathay_united_practice/service` | Chứa các **Unit Tests** và **Integration Tests** cho các lớp dịch vụ. |

### Cấu trúc Tệp Gốc

| Tệp | Mô tả |
| :--- | :--- |
| `Dockerfile` | Định nghĩa image Docker cho ứng dụng Spring Boot. |
| `docker-compose.yml` | Định nghĩa các service Docker (`app` và `db`). |
| `pom.xml` | Cấu hình dự án Maven. |
| `README.md` | Tài liệu hướng dẫn này. |
