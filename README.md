# 🚪 Space Booking Engine

> **이펙티브 자바(Effective Java 3/E) 원칙을 적용한 Pure Java 기반 고가용성 스터디룸 예약 및 요금 계산 엔진**

`space-booking-engine`은 공용 사무실 및 스터디룸 예약 서비스에서 발생할 수 있는 **시간 슬롯 중복, 동시성 예약 충돌, 미승인 상태 변경, 요금 할인 계산 오차**를 안전하게 처리하는 순수 자바 백엔드 엔진입니다.

프론트엔드 프레임워크나 외부 DB에 의존하지 않고, **불변 객체, Enum 기반 상태 캡슐화, 전략 패턴** 등 자바 코어 수준의 객체지향 아키텍처를 깊게 다루는 데 초점을 맞췄습니다.

---

## 🛠️ Tech Stack & Environment

- **Language**: Java 21
- **Build Tool**: Pure Java / Gradle
- **Architecture**: DDD Rich Domain Model, Strategy Pattern, Value Object (VO)

---

## 🏗️ Step-by-Step Architecture

```text
[1단계] Main.java 기반 시간 중복 검증 연산 공식 검증
   ↓
[2단계] ReservationTime, Money 불변 값 객체(VO) 및 정적 팩터리 메서드 구현
   ↓
[3단계] ReservationStatus Enum 설계 및 상태 전환 규칙 캡슐화
   ↓
[4단계] DiscountPolicy 전략 패턴 및 ReservationService / Repository 비즈니스 통합
```

---

## 💡 Key Design & Effective Java Principles

### 1. 시간 및 요금 데이터의 불변 객체(VO) 설계 (`Item 17`, `Item 1`)
- **`ReservationTime` & `Money`**:
  - `final` 키워드와 `private` 생성자를 적용하여 객체의 생성 이후 상태 변경을 원천 차단했습니다.
  - 정적 팩터리 메서드(`of()`, `won()`)를 제공하여 생성 목적을 명확히 하고, 생성 시점에 **시작 시간이 종료 시간보다 늦을 수 없다**는 비즈니스 방어 로직을 수행합니다.

### 2. 시간 슬롯 중복 검증 알고리즘 캡슐화
- **`ReservationTime.isOverlappedWith()`**:
  - `(A.start < B.end) && (A.end > B.start)` 공식을 객체 내부로 캡슐화하여, 외부 서비스 레이어의 복잡한 조건문 없이 시간 슬롯 겹침을 명확히 판별합니다.

### 3. Enum 기반 상태 관리 및 상태 전환 검증 (`Item 34`)
- **`ReservationStatus`**:
  - 단순 문자열이나 정수 상수가 아닌 열거 타입(Enum)을 활용해 상태 종류(`REQUESTED`, `CONFIRMED`, `COMPLETED`, `CANCELLED`)를 엄격하게 제한했습니다.
  - Enum 내부 `canTransitionTo()` 메서드에 **Java 21 Switch Expression**을 적용하여, '이용 완료'나 '취소' 상태에서 다른 상태로 유효하지 않게 변경되는 비즈니스 버그를 차단했습니다.

### 4. 유연한 요금 할인 전략 패턴 (`Item 38`)
- **`DiscountPolicy` 인터페이스**:
  - 정액 할인(`FixDiscountPolicy`), 학생 비율 할인(`StudentDiscountPolicy`) 등 다양한 할인 정책을 인터페이스로 다변화하여, 기존 코드 수정 없이 새로운 할인 정책을 추가할 수 있는 **개방-폐쇄 원칙(OCP)** 을 준수했습니다.

---