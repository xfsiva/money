/*
 * Copyright 2012-2015 Comcast Cable Communications Management, LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.comcast.money.core

import com.comcast.money.util.DateTimeUtil

trait Note[T] {
  val name: String
  val value: Option[T]
  val timestamp: Long
}

case class BooleanNote(name: String, value: Option[Boolean], timestamp: Long = DateTimeUtil.microTime)
  extends Note[Boolean]
case class StringNote(name: String, value: Option[String], timestamp: Long = DateTimeUtil.microTime)
  extends Note[String]
case class LongNote(name: String, value: Option[Long], timestamp: Long = DateTimeUtil.microTime) extends Note[Long]
case class DoubleNote(name: String, value: Option[Double], timestamp: Long = DateTimeUtil.microTime)
  extends Note[Double]

object Note {

  /**
   * Convenience method to create a note with a name but no value
   * @param name The name of the note being created
   * @return a [[LongNote]] with None for a value and the timestamp set to now
   */
  def apply(name: String): LongNote = LongNote(name, None)

  def apply(name: String, t: Long, timestamp: Long) = LongNote(name, Option(t), timestamp)

  def apply(name: String, t: Long) = LongNote(name, Option(t))

  def apply(name: String, t: String, timestamp: Long) = StringNote(name, Option(t), timestamp)

  def apply(name: String, t: String) = StringNote(name, Option(t))

  def apply(name: String, t: Boolean, timestamp: Long) = BooleanNote(name, Option(t), timestamp)

  def apply(name: String, t: Boolean) = BooleanNote(name, Option(t))

  def apply(name: String, t: Double, timestamp: Long) = DoubleNote(name, Option(t), timestamp)

  def apply(name: String, t: Double) = DoubleNote(name, Option(t))
}

object Result {
  def failed: Note[Boolean] = Note("span-success", false)
  def success: Note[Boolean] = Note("span-success", true)
}
