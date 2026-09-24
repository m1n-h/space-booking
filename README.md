# 🚪 Space Booking Engine

> **이펙티브 자바(Effective Java 3/E) 원칙을 적용한 Pure Java 기반 고가용성 스터디룸 예약 및 요금 계산 엔진**

`space-booking-engine`은 공용 사무실 및 스터디룸 예약 서비스에서 발생할 수 있는 **시간 슬롯 중복, 동시성 예약 충돌, 미승인 상태 변경, 요금 할인 계산 오차**를 안전하게 처리하는 순수 자바 백엔드 엔진입니다.

프론트엔드 프레임워크나 외부 DB에 의존하지 않고, **불변 객체, Enum 기반 상태 캡슐화, 전략 패턴** 등 자바 코어 수준의 객체지향 아키텍처를 깊게 다루는 데 초점을 맞췄습니다.

---

## 🏗️ Step-by-Step Roadmap

- [x] **1단계: 시간 중복 검증 알고리즘 (Main.java)**
    - 기초 `LocalTime` 비교 로직을 활용한 시간 슬롯 겹침 판별
- [ ] **2단계: 불변 도메인 모델 설계 (Item 17, Item 1)**
    - `ReservationTime`, `Money` 등 도메인 값 객체(VO) 불변화 및 정적 팩터리 메서드 적용
- [ ] **3단계: Enum 기반 상태 관리 캡슐화 (Item 34)**
    - 예약 상태(`REQUESTED`, `CONFIRMED`, `CANCELLED` 등)의 안전한 상태 전환 검증
- [ ] **4단계: 유연한 요금 할인 전략 패턴 (Item 38)**
    - 학생 할인, 평일/주말 할인 정책 분리 및 예약을 관리하는 Repository & Service 완성

---

## 🛠️ Tech Stack & Environment

- **Language**: Java 21
- **Build Tool**: Gradle (or Pure Java Environment)
- **Design Pattern**: DDD Rich Domain Model, Strategy Pattern, Immutable Object